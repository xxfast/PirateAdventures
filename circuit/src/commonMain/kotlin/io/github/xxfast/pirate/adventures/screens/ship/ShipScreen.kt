package io.github.xxfast.pirate.adventures.screens.ship

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
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen
import io.github.xxfast.pirate.adventures.screens.ship.ShipEvent.GotoDock
import io.github.xxfast.pirate.adventures.screens.ship.ShipEvent.GotoIsland
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen.State
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.utils.SlideNavDecoration

sealed interface ShipEvent : CircuitUiEvent {
  data object GotoIsland : ShipEvent
  data object GotoDock : ShipEvent
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
    GotoIsland -> navigator.pop()
    GotoDock -> navigator.goTo(DockScreen)
  }
}

@Composable
fun ShipUi(state: State) {
  Slide(
    onNext = { state.eventSink(GotoDock) },
    onPrevious = { state.eventSink(GotoIsland) }
  ) {
    val backstack: SaveableBackStack = rememberSaveableBackStack { push(GangwayScreen) }
    val navigator: Navigator = rememberCircuitNavigator(
      backstack = backstack,
      onRootPop = { state.eventSink(GotoIsland) }
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

class ShipPresenterFactory : Presenter.Factory {
  override fun create(
    screen: Screen,
    navigator: Navigator,
    context: CircuitContext,
  ): Presenter<*>? = when (screen) {
    is GangwayScreen -> presenterOf { GangwayPresenter(navigator) }
    is DeckScreen -> presenterOf { DeckPresenter(navigator) }
    is CrowsNestScreen -> presenterOf { CrowsNestPresenter(navigator) }
    else -> null
  }
}

class ShipUiFactory : Ui.Factory {
  override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
    is GangwayScreen -> ui<GangwayScreen.State> { state, _ -> GangwayUi(state) }
    is DeckScreen -> ui<DeckScreen.State> { state, _ -> DeckUi(state) }
    is CrowsNestScreen -> ui<CrowsNestScreen.State> { state, _ -> CrowsNestUi(state) }
    else -> null
  }
}
