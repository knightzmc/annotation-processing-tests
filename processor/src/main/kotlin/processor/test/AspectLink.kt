package processor.test

import kotlin.reflect.KClass

/**
 * @author Alexander Wood (BristerMitten)
 */

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class AspectLink(val aspect: KClass<out Aspect>)
