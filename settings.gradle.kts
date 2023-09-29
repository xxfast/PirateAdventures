pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PirateAdventures"
include(":decompose-router")
include(":shared")
include(":androidx-navigation-compose")
include(":voyager")
