package processor.test

/**
 * @author Alexander Wood (BristerMitten)
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class AspectInfo(val name: String)
