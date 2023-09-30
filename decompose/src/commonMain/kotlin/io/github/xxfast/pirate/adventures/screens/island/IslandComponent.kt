package io.github.xxfast.pirate.adventures.screens.island

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView

class IslandComponent(
  componentContext: ComponentContext,
  val onNext: () -> Unit,
) : ComponentContext by componentContext

@Composable
fun IslandUi(component: IslandComponent) {
  Slide(onNext = component.onNext) {
    IslandView()
  }
}
