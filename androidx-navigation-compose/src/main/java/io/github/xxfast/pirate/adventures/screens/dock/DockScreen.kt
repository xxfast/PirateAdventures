package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.ShipView
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView

@Composable
fun DockScreen() {
  val navController: NavHostController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "ship",
    enterTransition = { slideIntoContainer(SlideDirection.Up) },
    exitTransition = { slideOutOfContainer(SlideDirection.Up) },
    popEnterTransition = { slideIntoContainer(SlideDirection.Down) },
    popExitTransition = { slideOutOfContainer(SlideDirection.Down) },
    modifier = Modifier.fillMaxSize()
  ) {
    composable("ship") {
      Slide(
        onNext = { navController.navigate("wharf") },
        orientation = Vertical,
        inverted = false,
      ) {
        ShipView()
      }
    }

    composable("wharf") {
      Slide(
        onPrevious = { navController.popBackStack() },
        orientation = Vertical,
        inverted = false,
      ) {
        DeckView()
      }
    }
  }
}
