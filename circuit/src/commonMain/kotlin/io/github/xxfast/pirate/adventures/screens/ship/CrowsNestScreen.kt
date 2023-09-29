package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.xxfast.pirate.adventures.screens.ship.CrowsNestEvent.GotoDeck
import io.github.xxfast.pirate.adventures.screens.ship.CrowsNestScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.ship.CrowsNestView

sealed interface CrowsNestEvent : CircuitUiEvent {
  data object GotoDeck : CrowsNestEvent
}

@Parcelize
data object CrowsNestScreen : Screen {
  data class State(
    val eventSink: (CrowsNestEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun CrowsNestPresenter(navigator: Navigator) = State { event ->
  when(event){
    GotoDeck -> navigator.pop()
  }
}

@Composable
fun CrowsNestUi(state: State) {
  Slide(
    onPrevious = { state.eventSink(GotoDeck) },
    orientation = Vertical,
    inverted = true,
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray),
    ) {
      CrowsNestView()
    }
  }
}
