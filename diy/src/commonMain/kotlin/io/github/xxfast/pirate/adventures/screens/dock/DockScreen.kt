package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.github.xxfast.pirate.adventures.screens.MainScreens.Dock
import io.github.xxfast.pirate.adventures.screens.dock.DockScreens.Ship
import io.github.xxfast.pirate.adventures.screens.dock.DockScreens.Wharf
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.ShipView
import io.github.xxfast.pirate.adventures.shared.screens.dock.WharfView

sealed interface DockScreens {
  data object Ship: DockScreens
  data object Wharf: DockScreens
}

@Composable
fun DockScreen() {
  var screens: List<DockScreens> by rememberSaveable { mutableStateOf(listOf(Ship)) }

  AnimatedContent(screens.last()){ screen ->
    when(screen){
      Ship -> Slide (
        onNext = { screens = screens + Wharf },
        orientation = Vertical,
      ) {
        ShipView()
      }

      Wharf -> Slide(
        onPrevious = { screens = screens - Wharf },
        orientation = Vertical,
      ) {
        WharfView()
      }
    }
  }
}
