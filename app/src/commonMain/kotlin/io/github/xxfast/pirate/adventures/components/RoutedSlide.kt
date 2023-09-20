package io.github.xxfast.pirate.adventures.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RoutedSlide(
  orientation: Orientation = Horizontal,
  onNext: (() -> Unit)? = null,
  onPrevious: (() -> Unit)? = null,
  content: @Composable () -> Unit,
) {
  Box {
    content()

    if (onPrevious != null) IconButton(
      onClick = onPrevious,
      modifier = Modifier
        .let {
          if (orientation == Horizontal) it.align(Alignment.CenterStart)
          else it.align(Alignment.TopCenter)
        }
        .windowInsetsPadding(WindowInsets.statusBars)
        .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
      Icon(
        imageVector =
        if (orientation == Horizontal) Icons.Rounded.ArrowBack
        else Icons.Rounded.ArrowUpward,
        contentDescription = null
      )
    }

    if (onNext != null) IconButton(
      onClick = onNext,
      modifier = Modifier
        .let {
          if (orientation == Horizontal) it.align(Alignment.CenterEnd)
          else it.align(Alignment.BottomCenter)
        }
        .windowInsetsPadding(WindowInsets.statusBars)
        .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
      Icon(
        imageVector =
        if (orientation == Horizontal) Icons.Rounded.ArrowForward
        else Icons.Rounded.ArrowDownward,
        contentDescription = null
      )
    }
  }
}
