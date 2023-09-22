package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.pirate.adventures.utils.BrowserViewportWindow
import io.github.xxfast.pirate.adventures.screens.MainScreen
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  onWasmReady {
    val lifecycle = LifecycleRegistry()
    val rootRouterContext = RouterContext(lifecycle = lifecycle)

    BrowserViewportWindow("Pirate Adventures 🏴‍☠️") {
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
