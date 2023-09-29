package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.slack.circuit.foundation.CircuitCompositionLocals
import io.github.xxfast.pirate.adventures.screens.MainScreen

fun main() {
  application {
    val windowState: WindowState = rememberWindowState()

    Window(
      title = "Pirate Adventures 🏴‍☠️",
      state = windowState,
      onCloseRequest = { exitApplication() }
    ) {
      CircuitCompositionLocals(circuit) {
        MaterialTheme {
          MainScreen {}
        }
      }
    }
  }
}
