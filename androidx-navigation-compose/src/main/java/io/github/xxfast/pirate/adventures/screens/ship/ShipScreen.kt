package io.github.xxfast.pirate.adventures.screens.ship

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
import io.github.xxfast.pirate.adventures.shared.screens.ship.CrowsNestView
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView

@Composable
fun ShipScreen() {
  val navController: NavHostController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "gangway",
    enterTransition = { slideIntoContainer(SlideDirection.Down) },
    exitTransition = { slideOutOfContainer(SlideDirection.Down) },
    popEnterTransition = { slideIntoContainer(SlideDirection.Up) },
    popExitTransition = { slideOutOfContainer(SlideDirection.Up) },
    modifier = Modifier.fillMaxSize()
  ) {
    composable("gangway") {
      Slide(
        onNext = { navController.navigate("deck") },
        orientation = Vertical,
        inverted = true,
      ) {
        GangwayView()
      }
    }

    composable("deck") {
      Slide(
        onNext = { navController.navigate("crowsNest") },
        onPrevious = { navController.popBackStack() },
        orientation = Vertical,
        inverted = true,
      ) {
        DeckView()
      }
    }

    composable("crowsNest") {
      Slide(
        onPrevious = { navController.popBackStack() },
        orientation = Vertical,
        inverted = true,
      ) {
        CrowsNestView()
      }
    }
  }
}
