package io.github.xxfast.pirate.adventures.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import io.github.xxfast.pirate.adventures.screens.island.IslandScreen

@Composable
fun MainScreen() {
  Navigator(IslandScreen) { navigator ->
    SlideTransition(navigator)
  }
}
