package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView

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
