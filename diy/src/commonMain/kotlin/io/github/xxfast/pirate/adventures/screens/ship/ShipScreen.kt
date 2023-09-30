package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreens.CrowsNest
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreens.Deck
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreens.Gangway
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.CrowsNestView
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView

sealed interface ShipScreens {
  data object Gangway: ShipScreens
  data object Deck: ShipScreens
  data object CrowsNest: ShipScreens
}

@Composable
fun ShipScreen() {
  var screens: List<ShipScreens> by rememberSaveable { mutableStateOf(listOf(Gangway)) }

  AnimatedContent(screens.last()){ screen ->
    when(screen){
      Gangway -> Slide(
        onNext = { screens = screens + Deck },
        orientation = Vertical,
        inverted = true,
      ) {
        GangwayView()
      }

      Deck -> Slide(
        onNext = { screens = screens + CrowsNest },
        onPrevious = { screens = screens - Deck },
        orientation = Vertical,
        inverted = true,
      ) {
        DeckView()
      }

      CrowsNest -> Slide(
        onPrevious = { screens = screens - CrowsNest },
        orientation = Vertical,
        inverted = true,
      ) {
        CrowsNestView()
      }
    }
  }
}
