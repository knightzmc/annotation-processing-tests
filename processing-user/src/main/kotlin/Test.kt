import bindings.DaggerMainComponent

/**
 * @author Alexander Wood (BristerMitten)
 */

fun main() {
    val component = DaggerMainComponent.create()
    println(component.getCommandAspect().getName())
    component.getAllAspects().forEach {
        it.enable()
    }
}
