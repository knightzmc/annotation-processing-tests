plugins {
    base
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

group = "org.example"
version = "1.0-SNAPSHOT"



allprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    repositories {
        mavenCentral()
    }
    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation("com.google.dagger:dagger:2.25.4")
    }
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
