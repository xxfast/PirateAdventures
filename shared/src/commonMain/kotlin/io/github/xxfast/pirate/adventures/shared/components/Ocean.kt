package io.github.xxfast.pirate.adventures.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.xxfast.pirate.adventures.shared.components.WavyShape

@Composable
fun Ocean(
  modifier: Modifier
) {
  // TODO: Animate these
  Box(
    modifier = modifier
      .background(Color.Blue, WavyShape(period = 256.dp, amplitude = 16.dp))
  )
}
