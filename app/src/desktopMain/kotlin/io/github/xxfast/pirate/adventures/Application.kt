package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.pirate.adventures.screens.MainScreen

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
  val lifecycle = LifecycleRegistry()
  val rootRouterContext = RouterContext(lifecycle = lifecycle)

  application {
    val windowState: WindowState = rememberWindowState()
    LifecycleController(lifecycle, windowState)

    Window(
      title = "Pirate Adventures 🏴‍☠️",
      state = windowState,
      onCloseRequest = { exitApplication() }
    ) {
      CompositionLocalProvider(
        LocalRouterContext provides rootRouterContext,
      ) {
        MaterialTheme {
          MainScreen()
        }
      }
    }
  }
}
