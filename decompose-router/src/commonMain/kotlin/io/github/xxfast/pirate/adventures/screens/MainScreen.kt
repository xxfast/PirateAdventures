package io.github.xxfast.pirate.adventures.screens

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.screens.MainScreens.Dock
import io.github.xxfast.pirate.adventures.screens.MainScreens.Island
import io.github.xxfast.pirate.adventures.screens.MainScreens.Ship
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen

@Parcelize
sealed class MainScreens : Parcelable {
  data object Island: MainScreens()
  data object Ship: MainScreens()
  data object Dock: MainScreens()
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainScreen() {
  val router: Router<MainScreens> = rememberRouter(MainScreens::class) { listOf(Island) }

  RoutedContent(
    router = router,
    animation = predictiveBackAnimation(
      backHandler = LocalRouterContext.current.backHandler,
      onBack = { router.pop() },
      animation = stackAnimation(slide(animationSpec = tween(500)))
    )
  ){ screen ->
    when(screen){
      Island -> Slide(onNext = { router.push(Ship) }) {
        IslandView()
      }

      Ship -> Slide(
        onNext = { router.push(Dock) },
        onPrevious = { router.pop() }
      ) {
        ShipScreen()
      }

      Dock -> Slide(
        onPrevious = { router.pop() }
      ){
        DockScreen()
      }
    }
  }
}

