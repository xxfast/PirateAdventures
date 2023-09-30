package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView

class DeckComponent(
  componentContext: ComponentContext,
  val onNext: () -> Unit,
  val onPrevious: () -> Unit,
) : ComponentContext by componentContext

@Composable
fun DeckUi(component: DeckComponent) {
  Slide(
    onNext = component.onNext,
    onPrevious = component.onPrevious,
    orientation = Vertical,
    inverted = true
  ) {
    DeckView()
  }
}
