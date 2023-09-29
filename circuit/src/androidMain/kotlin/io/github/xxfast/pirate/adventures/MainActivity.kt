package io.github.xxfast.pirate.adventures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.CircuitCompositionLocals
import io.github.xxfast.pirate.adventures.screens.MainScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CircuitCompositionLocals(circuit) {
        MaterialTheme {
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
          ) {
            MainScreen(
              onBack = { onBackPressed() }
            )
          }
        }
      }
    }
  }
}
