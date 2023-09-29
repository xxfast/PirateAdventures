package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideOrientation
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.CrowsNestView
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView
import io.github.xxfast.pirate.adventures.utils.SlideTransition

object ShipScreen : Screen {
  @Composable
  override fun Content() {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    Slide(
      onNext = { navigator.push(DockScreen) },
      onPrevious = { navigator.pop() },
    ) {
      Navigator(GangwayScreen) { navigator ->
        SlideTransition(
          navigator = navigator,
          orientation = SlideOrientation.Vertical,
          inverse = true,
        )
      }
    }
  }
}

object GangwayScreen : Screen {
  @Composable
  override fun Content() {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    Slide(
      onNext = { navigator.push(DeckScreen) },
      orientation = Vertical,
      inverted = true,
    ) {
      GangwayView()
    }
  }
}

object DeckScreen: Screen {
  @Composable
  override fun Content() {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    Slide(
      onNext = { navigator.push(CrowsNestScreen) },
      onPrevious = { navigator.pop() },
      orientation = Vertical,
      inverted = true,
    ) {
      DeckView()
    }
  }
}

object CrowsNestScreen: Screen {
  @Composable
  override fun Content() {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    Slide(
      onPrevious = { navigator.pop() },
      orientation = Vertical,
      inverted = true,
    ) {
      CrowsNestView()
    }
  }
}
