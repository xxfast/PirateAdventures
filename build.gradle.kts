plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.2.0-alpha12").apply(false)
    id("com.android.library").version("8.2.0-alpha12").apply(false)
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.5.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
