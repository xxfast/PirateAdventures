package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.xxfast.pirate.adventures.screens.dock.ShipEvent.GotoWharf
import io.github.xxfast.pirate.adventures.screens.dock.ShipScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.ShipView

sealed interface ShipEvent : CircuitUiEvent {
  data object GotoWharf : ShipEvent
}

@Parcelize
data object ShipScreen : Screen {
  data class State(
    val eventSink: (ShipEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun ShipPresenter(navigator: Navigator) = State { event ->
  when(event) {
    GotoWharf -> navigator.goTo(WharfScreen)
  }
}

@Composable
fun ShipUi(state: State) {
  Slide(
    onNext = { state.eventSink(GotoWharf) },
    orientation = Vertical
  ) {
    ShipView()
  }
}


