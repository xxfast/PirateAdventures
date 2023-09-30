package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.CrowsNestView

class CrowsNestComponent(
  componentContext: ComponentContext,
  val onPrevious: () -> Unit,
) : ComponentContext by componentContext

@Composable
fun CrowsNestUi(component: CrowsNestComponent) {
  Slide(
    onPrevious = component.onPrevious,
    orientation = Vertical,
    inverted = true
  ) {
    CrowsNestView()
  }
}
