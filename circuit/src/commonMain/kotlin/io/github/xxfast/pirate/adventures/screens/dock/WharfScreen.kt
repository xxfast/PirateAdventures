package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.xxfast.pirate.adventures.screens.dock.WharfEvent.GotoShip
import io.github.xxfast.pirate.adventures.screens.dock.WharfScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.WharfView

sealed interface WharfEvent : CircuitUiEvent {
  data object GotoShip : WharfEvent
}

@Parcelize
data object WharfScreen : Screen {
  data class State(
    val eventSink: (WharfEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun WharfPresenter(navigator: Navigator) = State { event ->
  when(event) {
    GotoShip -> navigator.pop()
  }
}

@Composable
fun WharfUi(state: State) {
  Slide(
    onPrevious = { state.eventSink(GotoShip) },
    orientation = Vertical
  ) {
    WharfView()
  }
}


