package io.github.xxfast.pirate.adventures.shared.screens.ship

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.xxfast.pirate.adventures.shared.components.Ocean

@Composable
fun GangwayView() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Gray),
  ) {
    Ocean(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.25f)
        .align(Alignment.BottomCenter)
    )
  }
}
