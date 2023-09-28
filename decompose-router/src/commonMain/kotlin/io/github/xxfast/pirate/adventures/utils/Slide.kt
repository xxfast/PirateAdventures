package io.github.xxfast.pirate.adventures.utils

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimator

private fun Modifier.offsetXFactor(factor: Float): Modifier =
  layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    layout(placeable.width, placeable.height) {
      placeable.placeRelative(x = (placeable.width.toFloat() * factor).toInt(), y = 0)
    }
  }

private fun Modifier.offsetYFactor(factor: Float): Modifier =
  layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    layout(placeable.width, placeable.height) {
      placeable.placeRelative(x = 0, y = (placeable.height.toFloat() * factor).toInt())
    }
  }


fun slide(
  animationSpec: FiniteAnimationSpec<Float> = tween(),
  orientation: Orientation = Horizontal,
  inverted: Boolean = false
): StackAnimator =
  stackAnimator(animationSpec = animationSpec) { factor, _, content ->
    val offset: Float = if (inverted) factor * -1f else factor
    content(
      when (orientation) {
        Horizontal -> Modifier.offsetXFactor(offset)
        Vertical -> Modifier.offsetYFactor(offset)
      }
    )
  }
