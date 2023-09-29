package io.github.xxfast.pirate.adventures.shared.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier

@Composable
fun Slide(
  orientation: Orientation = Horizontal,
  inverted: Boolean = false,
  onNext: (() -> Unit)? = null,
  onPrevious: (() -> Unit)? = null,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(modifier.fillMaxSize()) {
    content()

    if (onPrevious != null) IconButton(
      onClick = onPrevious,
      modifier = Modifier
        .let {
          if (orientation == Horizontal) it.align(if (inverted) Alignment.CenterEnd else Alignment.CenterStart  )
          else it.align(if (inverted) Alignment.BottomCenter else Alignment.TopCenter)
        }
        .windowInsetsPadding(WindowInsets.statusBars)
        .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
      Icon(
        imageVector = when {
          orientation == Horizontal && !inverted -> Icons.Rounded.ArrowBack
          orientation == Horizontal && inverted -> Icons.Rounded.ArrowForward
          orientation == Vertical && !inverted -> Icons.Rounded.ArrowUpward
          else -> Icons.Rounded.ArrowDownward
        },
        contentDescription = null
      )
    }

    if (onNext != null) IconButton(
      onClick = onNext,
      modifier = Modifier
        .let {
          if (orientation == Horizontal) it.align(if (inverted) Alignment.CenterStart else Alignment.CenterEnd )
          else it.align(if (inverted) Companion.TopCenter else Alignment.BottomCenter)
        }
        .windowInsetsPadding(WindowInsets.statusBars)
        .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
      Icon(
        imageVector =
        when  {
          orientation == Horizontal && !inverted -> Icons.Rounded.ArrowForward
          orientation == Horizontal && inverted -> Icons.Rounded.ArrowBack
          orientation == Vertical && !inverted -> Icons.Rounded.ArrowDownward
          else -> Icons.Rounded.ArrowUpward
        },
        contentDescription = null
      )
    }
  }
}
