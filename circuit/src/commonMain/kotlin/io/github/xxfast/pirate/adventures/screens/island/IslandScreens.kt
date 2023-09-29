package io.github.xxfast.pirate.adventures.screens.island

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.xxfast.pirate.adventures.screens.island.IslandEvent.GotoShip
import io.github.xxfast.pirate.adventures.screens.island.IslandScreens.State
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView

sealed interface IslandEvent : CircuitUiEvent {
  data object GotoShip : IslandEvent
}

@Parcelize
data object IslandScreens : Screen {
  data class State(
    val eventSink: (IslandEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun IslandPresenter(navigator: Navigator) = State { navigator.goTo(ShipScreen) }

@Composable
fun IslandUi(state: State) {
  Slide(onNext = { state.eventSink(GotoShip) }) {
    IslandView()
  }
}
