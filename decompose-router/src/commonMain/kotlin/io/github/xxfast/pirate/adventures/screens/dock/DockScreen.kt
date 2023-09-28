package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter
import io.github.xxfast.pirate.adventures.components.RoutedSlide
import io.github.xxfast.pirate.adventures.screens.dock.DockScreens.Ship
import io.github.xxfast.pirate.adventures.screens.dock.DockScreens.Wharf
import io.github.xxfast.pirate.adventures.utils.slide

@Parcelize
sealed class DockScreens : Parcelable {
  data object Ship : DockScreens()
  data object Wharf : DockScreens()
}

@Composable
fun DockScreen() {
  val router: Router<DockScreens> = rememberRouter(DockScreens::class) { listOf(Ship) }

  RoutedContent(
    router = router,
    animation = stackAnimation(
      slide(
        animationSpec = tween(500),
        orientation = Vertical,
        inverted = false
      )
    )
  ) { screen ->
    when (screen) {
      Ship -> RoutedSlide(
        orientation = Vertical,
        onNext = { router.push(Wharf) },
        inverted = false,
      ) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        )
      }

      Wharf -> RoutedSlide(
        orientation = Vertical,
        onPrevious = { router.pop() },
        inverted = false,
      ) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        )
      }
    }
  }
}
