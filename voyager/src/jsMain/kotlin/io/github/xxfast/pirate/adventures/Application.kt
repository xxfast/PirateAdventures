package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import io.github.xxfast.pirate.adventures.screens.MainScreen
import io.github.xxfast.pirate.adventures.utils.BrowserViewportWindow
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  onWasmReady {
    BrowserViewportWindow("Pirate Adventures 🏴‍☠️") {
      MaterialTheme {
        MainScreen()
      }
    }
  }
}
