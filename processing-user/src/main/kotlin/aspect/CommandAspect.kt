package aspect

import processor.test.Aspect
import processor.test.AspectInfo

/**
 * @author Alexander Wood (BristerMitten)
 */
@AspectInfo("Commands")
abstract class CommandAspect : Aspect {

    override fun enable() {
        println("enabled")
    }
}
