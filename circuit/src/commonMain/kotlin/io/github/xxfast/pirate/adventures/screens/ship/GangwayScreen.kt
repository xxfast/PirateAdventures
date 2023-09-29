package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.xxfast.pirate.adventures.screens.ship.GangwayEvent.GotoDeck
import io.github.xxfast.pirate.adventures.screens.ship.GangwayScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView

sealed interface GangwayEvent : CircuitUiEvent {
  data object GotoDeck : GangwayEvent
}

@Parcelize
data object GangwayScreen : Screen {
  data class State(
    val eventSink: (GangwayEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun GangwayPresenter(navigator: Navigator) = State { navigator.goTo(DeckScreen) }

@Composable
fun GangwayUi(state: State) {
  Slide(
    onNext = { state.eventSink(GotoDeck) },
    orientation = Vertical,
    inverted = true,
  ) {
    GangwayView()
  }
}
