package io.github.xxfast.pirate.adventures.screens.island

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView

object IslandScreen : Screen {
  @Composable
  override fun Content() {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    Slide(
      onNext = { navigator.push(ShipScreen) }
    ) {
      IslandView()
    }
  }
}
