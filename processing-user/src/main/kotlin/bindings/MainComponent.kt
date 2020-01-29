package bindings

import aspect.CommandAspect
import aspect.CommandAspectModule
import dagger.Component
import processor.test.Aspect
import javax.inject.Singleton

/**
 * @author Alexander Wood (BristerMitten)
 */
@Singleton
@Component(modules = [CommandAspectModule::class])
interface MainComponent {
    fun getCommandAspect() : CommandAspect

    fun getAllAspects() : Set<Aspect>
}
