package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import io.github.xxfast.pirate.adventures.screens.MainScreen
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCValues
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplicationMain
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun main() {
  val args = emptyArray<String>()
  memScoped {
    val argc = args.size + 1
    val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
    autoreleasepool {
      UIApplicationMain(argc, argv, null, NSStringFromClass(AppDelegate))
    }
  }
}

fun MainUIViewController(): UIViewController {
  return ComposeUIViewController {
    MaterialTheme {
      MainScreen()
    }
  }
}
