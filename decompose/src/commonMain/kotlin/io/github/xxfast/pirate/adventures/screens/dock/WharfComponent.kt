package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.ShipView
import io.github.xxfast.pirate.adventures.shared.screens.dock.WharfView

class WharfComponent(
  componentContext: ComponentContext,
  val onPrevious: () -> Unit,
) : ComponentContext by componentContext

@Composable
fun WharfUi(component: WharfComponent) {
  Slide(
    onPrevious = component.onPrevious,
    orientation = Vertical,
  ) {
    WharfView()
  }
}
