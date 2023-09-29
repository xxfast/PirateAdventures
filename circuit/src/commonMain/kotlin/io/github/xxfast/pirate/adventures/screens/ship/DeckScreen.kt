package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.xxfast.pirate.adventures.screens.ship.DeckEvent.GotoCrowsNest
import io.github.xxfast.pirate.adventures.screens.ship.DeckEvent.GotoGangway
import io.github.xxfast.pirate.adventures.screens.ship.DeckScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView

sealed interface DeckEvent : CircuitUiEvent {
  data object GotoGangway : DeckEvent
  data object GotoCrowsNest : DeckEvent
}

@Parcelize
data object DeckScreen : Screen {
  data class State(
    val eventSink: (DeckEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun DeckPresenter(navigator: Navigator) = State { event ->
  when(event){
    GotoGangway -> navigator.pop()
    GotoCrowsNest -> navigator.goTo(CrowsNestScreen)
  }
}

@Composable
fun DeckUi(state: State) {
  Slide(
    onNext = { state.eventSink(GotoCrowsNest) },
    onPrevious = { state.eventSink(GotoGangway) },
    orientation = Vertical,
    inverted = true,
  ) {
    DeckView()
  }
}
