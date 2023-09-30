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
include(":diy")
include(":androidx-navigation-compose")
include(":decompose-router")
include(":decompose")
include(":voyager")
include(":circuit")
