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
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "app"
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
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
