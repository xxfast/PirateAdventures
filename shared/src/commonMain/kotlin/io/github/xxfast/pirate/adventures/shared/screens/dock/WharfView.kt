package io.github.xxfast.pirate.adventures.shared.screens.dock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WharfView() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black),
  ){
    Text(
      text = "Wharf",
      style = MaterialTheme.typography.displayLarge,
      modifier = Modifier.align(Alignment.Center)
    )
  }
}
