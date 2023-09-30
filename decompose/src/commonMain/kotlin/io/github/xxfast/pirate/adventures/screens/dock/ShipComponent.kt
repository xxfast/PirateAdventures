package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.ShipView

class ShipComponent(
  componentContext: ComponentContext,
  val onNext: () -> Unit,
) : ComponentContext by componentContext

@Composable
fun ShipUi(component: ShipComponent) {
  Slide(
    onNext = component.onNext,
    orientation = Vertical,
  ) {
    ShipView()
  }
}
