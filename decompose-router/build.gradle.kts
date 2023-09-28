import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi
import org.jetbrains.compose.experimental.dsl.IOSDevices

plugins {
  kotlin("multiplatform")
  id("com.android.application")
  id("org.jetbrains.compose")
  id("kotlin-parcelize")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
  targetHierarchy.default()

  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  listOf(
    iosX64("uikitX64"),
    iosArm64("uikitArm64"),
    iosSimulatorArm64("uikitSimulatorArm64"),
  ).forEach {
    it.binaries{
      executable {
        entryPoint = "io.github.xxfast.pirate.adventures.main"
        freeCompilerArgs += listOf(
          "-linker-option", "-framework", "-linker-option", "Metal",
          "-linker-option", "-framework", "-linker-option", "CoreText",
          "-linker-option", "-framework", "-linker-option", "CoreGraphics"
        )
      }
    }
  }

  jvm("desktop") {
    compilations.all {
      kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.getMajorVersion()
      }
    }
  }

  js(IR) {
    browser()
    binaries.executable()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(project(":shared"))

        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.materialIconsExtended)
        implementation(libs.decompose)
        implementation(libs.decompose.compose.multiplatform)
        implementation(libs.decompose.router)
        implementation(libs.essenty.parcelable)
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val androidMain by getting {
      dependencies {
        implementation(compose.uiTooling)
        implementation(libs.androidx.activity.ktx)
        implementation(libs.androidx.activity.compose)
      }
    }

    val desktopMain by getting {
      dependencies {
        implementation(compose.desktop.currentOs)
        implementation(libs.decompose.compose.multiplatform)
        implementation(libs.kotlinx.coroutines.swing)
      }
    }
  }
}

android {
  namespace = "io.github.xxfast.pirate.adventures"
  compileSdk = 34
  defaultConfig {
    minSdk = 24
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.0"
  }
}


compose.desktop {
  application {
    mainClass = "io.github.xxfast.pirate.adventures.ApplicationKt"

    nativeDistributions {
      targetFormats(Dmg, Msi, Deb)

      packageName = "App"
      packageVersion = "1.0.0"
    }
  }
}

compose.experimental {
  web.application {
  }

  uikit.application {
    bundleIdPrefix = "io.github.xxfast.pirate.adventures.decompose.router"
    projectName = "PirateAdventures"

    deployConfigurations {
      simulator("IPhone12Pro") {
        // Usage: ./gradlew iosDeployIPhone12ProDebug
        device = IOSDevices.IPHONE_12_PRO
      }

      /*
       * Usage: ./gradlew iosDeployDeviceDebug
       *
       * If your are getting "A valid provisioning profile for this executable was not found" error,
       * see: https://developer.apple.com/forums/thread/128121?answerId=403323022#403323022
       */
      connectedDevice("Device") {
        // Uncomment and fill with your Team ID
        // teamId = ""
      }
    }
  }
}
