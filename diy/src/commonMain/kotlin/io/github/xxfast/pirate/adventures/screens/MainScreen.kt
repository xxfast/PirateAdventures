package io.github.xxfast.pirate.adventures.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.github.xxfast.pirate.adventures.screens.MainScreens.Dock
import io.github.xxfast.pirate.adventures.screens.MainScreens.Island
import io.github.xxfast.pirate.adventures.screens.MainScreens.Ship
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView

sealed interface MainScreens {
  data object Island: MainScreens
  data object Ship: MainScreens
  data object Dock: MainScreens
}

@Composable
fun MainScreen() {
  var screens: List<MainScreens> by rememberSaveable { mutableStateOf(listOf(Island)) }

  AnimatedContent(
    targetState =  screens.last(),
    transitionSpec = {
      if (this.initialState in screens) slideIntoContainer(Start) togetherWith slideOutOfContainer(Start)
      else slideIntoContainer(End) togetherWith slideOutOfContainer(End)
    }
  ){ screen ->
    when(screen){
      Island -> Slide(
        onNext = { screens = screens + Ship }
      ) {
        IslandView()
      }

      Ship -> Slide(
        onNext = { screens = screens + Dock },
        onPrevious = { screens = screens - Ship }
      ) {
        ShipScreen()
      }

      Dock -> Slide(
        onPrevious = { screens = screens - Dock }
      ) {
        DockScreen()
      }
    }
  }
}
