package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView

class GangwayComponent(
  componentContext: ComponentContext,
  val onNext: () -> Unit,
) : ComponentContext by componentContext

@Composable
fun GangwayUi(component: GangwayComponent) {
  Slide(
    onNext = component.onNext,
    orientation = Vertical,
    inverted = true
  ) {
    GangwayView()
  }
}
