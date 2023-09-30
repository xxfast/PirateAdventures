package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.pirate.adventures.screens.MainComponent
import io.github.xxfast.pirate.adventures.screens.MainUi

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = DefaultComponentContext(lifecycle = lifecycle)
  val mainComponent = MainComponent(defaultComponentContext)

  application {
    val windowState: WindowState = rememberWindowState()
    LifecycleController(lifecycle, windowState)

    Window(
      title = "Pirate Adventures 🏴‍☠️",
      state = windowState,
      onCloseRequest = { exitApplication() }
    ) {
      MaterialTheme {
        MainUi(mainComponent)
      }
    }
  }
}
