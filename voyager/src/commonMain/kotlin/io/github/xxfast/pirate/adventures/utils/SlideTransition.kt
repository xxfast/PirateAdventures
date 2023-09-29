package io.github.xxfast.pirate.adventures.utils

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.stack.StackEvent.Pop
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import cafe.adriel.voyager.transitions.SlideOrientation
import cafe.adriel.voyager.transitions.SlideOrientation.Horizontal
import cafe.adriel.voyager.transitions.SlideOrientation.Vertical

@Composable
fun SlideTransition(
  navigator: Navigator,
  modifier: Modifier = Modifier,
  orientation: SlideOrientation = Horizontal,
  inverse: Boolean = false,
  animationSpec: FiniteAnimationSpec<IntOffset> = spring(
    stiffness = Spring.StiffnessMediumLow,
    visibilityThreshold = IntOffset.VisibilityThreshold
  ),
  content: ScreenTransitionContent = { it.Content() }
) {
  val direction = if (inverse) -1 else 1
  ScreenTransition(
    navigator = navigator,
    modifier = modifier,
    content = content,
    transition = {
      val (initialOffset, targetOffset) = when (navigator.lastEvent) {
        Pop -> ({ size: Int -> -size * direction }) to ({ size: Int -> size * direction })
        else -> ({ size: Int -> size * direction }) to ({ size: Int -> -size * direction })
      }

      when (orientation) {
        Horizontal ->
          slideInHorizontally(animationSpec, initialOffset) togetherWith
            slideOutHorizontally(animationSpec, targetOffset)

        Vertical ->
          slideInVertically(animationSpec, initialOffset) togetherWith
            slideOutVertically(animationSpec, targetOffset)
      }
    }
  )
}
