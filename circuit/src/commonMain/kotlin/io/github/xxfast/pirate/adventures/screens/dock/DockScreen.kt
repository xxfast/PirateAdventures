package io.github.xxfast.pirate.adventures.screens.dock

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.parcelable.Parcelize
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import io.github.xxfast.pirate.adventures.screens.dock.DockEvent.GotoShip
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.utils.SlideNavDecoration

sealed interface DockEvent : CircuitUiEvent {
  data object GotoShip : DockEvent
}

@Parcelize
data object DockScreen : Screen {
  data class State(
    val eventSink: (DockEvent) -> Unit = {},
  ) : CircuitUiState
}

@Composable
fun DockPresenter(navigator: Navigator) = State { event ->
  when(event) {
    GotoShip -> navigator.pop()
  }
}

@Composable
fun DockUi(state: State) {
  Slide(
    onPrevious = { state.eventSink(GotoShip) }
  ) {
    val backstack: SaveableBackStack = rememberSaveableBackStack { push(ShipScreen) }
    val navigator: Navigator = rememberCircuitNavigator(
      backstack = backstack,
      onRootPop = { state.eventSink(GotoShip) }
    )

    NavigableCircuitContent(
      navigator = navigator,
      backstack = backstack,
      decoration = GestureNavigationDecoration(
        fallback = SlideNavDecoration,
        onBackInvoked = navigator::pop
      )
    )
  }
}

class DockPresenterFactory : Presenter.Factory {
  override fun create(
    screen: Screen,
    navigator: Navigator,
    context: CircuitContext,
  ): Presenter<*>? = when (screen) {
    is ShipScreen -> presenterOf { ShipPresenter(navigator) }
    is WharfScreen -> presenterOf { WharfPresenter(navigator) }
    else -> null
  }
}

class DockUiFactory : Ui.Factory {
  override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
    is ShipScreen -> ui<ShipScreen.State> { state, _ -> ShipUi(state) }
    is WharfScreen -> ui<WharfScreen.State> { state, _ -> WharfUi(state) }
    else -> null
  }
}

