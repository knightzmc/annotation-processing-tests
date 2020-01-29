import aspect.CommandAspect
import processor.test.AspectLink

/**
 * @author Alexander Wood (BristerMitten)
 */

@AspectLink(CommandAspect::class)
annotation class Command(val name: String)
