package io.github.xxfast.pirate.adventures.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.NavDecoration
import kotlinx.collections.immutable.ImmutableList

object SlideNavDecoration : NavDecoration {
  @Composable
  override fun <T> DecoratedContent(
    args: ImmutableList<T>,
    backStackDepth: Int,
    modifier: Modifier,
    content: @Composable (T) -> Unit
  ) {
    AnimatedContent(
      targetState = args,
      modifier = modifier,
      transitionSpec = { slideInHorizontally(tween()) togetherWith slideOutHorizontally(tween()) },
    ) {
      content(it.first())
    }
  }
}
