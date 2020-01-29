package processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import processor.test.Aspect
import processor.test.AspectInfo
import javax.annotation.processing.*
import javax.inject.Singleton
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

/**
 * @author Alexander Wood (BristerMitten)
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class AspectProcessor : AbstractProcessor() {


    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(AspectInfo::class.java).forEach {
            createAspectImplementation(it).writeTo(processingEnv.filer)
            createAspectDaggerModule(it).writeTo(processingEnv.filer)
        }
        return false
    }

    private fun createAspectDaggerModule(it: Element): FileSpec {
        val simpleName = it.simpleName
        val name = "${simpleName}Module"

        val type = TypeSpec.classBuilder(name)
            .addAnnotation(Module::class)
            .addProperty(
                PropertySpec.builder("aspect", it.asType().asTypeName())
                    .addModifiers(KModifier.PRIVATE, KModifier.FINAL)
                    .initializer("Plumber$simpleName()")
                    .build()
            )
            .addFunction(
                FunSpec.builder("get$simpleName")
                    .addAnnotation(Provides::class)
                    .addAnnotation(Singleton::class)
                    .addStatement("return aspect")
                    .build()
            )
            .addFunction(
                FunSpec.builder("getAspect")
                    .addAnnotation(Provides::class)
                    .addAnnotation(Singleton::class)
                    .addAnnotation(IntoSet::class)
                    .returns(Aspect::class)
                    .addStatement("return aspect")
                    .build()
            )
            .build()
        return FileSpec.get(processingEnv.elementUtils.getPackageOf(it).qualifiedName.toString(), type)
    }

    private fun createAspectImplementation(it: Element): FileSpec {
        val name = "Plumber${it.simpleName}"
        val aspectName = it.getAnnotation(AspectInfo::class.java).name

        val type = TypeSpec.classBuilder(name)
            .superclass(it.asType().asTypeName())
            .addProperty(
                PropertySpec.builder("name", String::class)
                    .addModifiers(KModifier.PRIVATE)
                    .initializer("%S", aspectName)
                    .build()
            )
            .addFunction(
                FunSpec.builder("getName")
                    .addModifiers(KModifier.OVERRIDE)
                    .addStatement("return name")
                    .returns(String::class)
                    .build()
            )
            .build()

        return FileSpec.get(processingEnv.elementUtils.getPackageOf(it).qualifiedName.toString(), type)

    }
}

