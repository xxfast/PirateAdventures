package io.github.xxfast.pirate.adventures.screens

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView

@Composable
fun MainScreen() {
  val navController: NavHostController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "island",
    enterTransition = { slideIntoContainer(Start) },
    exitTransition = { slideOutOfContainer(Start) },
    popEnterTransition = { slideIntoContainer(End) },
    popExitTransition = { slideOutOfContainer(End) },
    modifier = Modifier.fillMaxSize()
  ) {
    composable("island") {
      Slide(onNext = { navController.navigate("ship") }) {
        IslandView()
      }
    }

    composable("ship") {
      Slide(
        onNext = { navController.navigate("dock") },
        onPrevious = { navController.popBackStack() }
      ) {
        ShipScreen()
      }
    }

    composable("dock") {
      Slide(
        onPrevious = { navController.popBackStack() }
      ) {
        DockScreen()
      }
    }
  }
}
