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
include(":shared")
include(":decompose-router")
include(":androidx-navigation-compose")
include(":voyager")
include(":circuit")
include(":diy")
