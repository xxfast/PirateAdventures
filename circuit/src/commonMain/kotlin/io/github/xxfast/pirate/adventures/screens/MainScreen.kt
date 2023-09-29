package io.github.xxfast.pirate.adventures.screens

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import io.github.xxfast.pirate.adventures.screens.dock.DockPresenter
import io.github.xxfast.pirate.adventures.screens.dock.DockScreen
import io.github.xxfast.pirate.adventures.screens.dock.DockUi
import io.github.xxfast.pirate.adventures.screens.island.IslandPresenter
import io.github.xxfast.pirate.adventures.screens.island.IslandUi
import io.github.xxfast.pirate.adventures.screens.island.IslandScreens
import io.github.xxfast.pirate.adventures.screens.ship.ShipPresenter
import io.github.xxfast.pirate.adventures.screens.ship.ShipUi
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreen
import io.github.xxfast.pirate.adventures.utils.SlideNavDecoration

@Composable
fun MainScreen(
  onBack: () -> Unit,
) {
  val backstack: SaveableBackStack = rememberSaveableBackStack { push(IslandScreens) }
  val navigator: Navigator = rememberCircuitNavigator(backstack, onBack)
  NavigableCircuitContent(
    navigator = navigator,
    backstack = backstack,
    decoration = GestureNavigationDecoration(
      fallback = SlideNavDecoration,
      onBackInvoked = navigator::pop
    )
  )
}

class MainPresenterFactory : Presenter.Factory {
  override fun create(
    screen: Screen,
    navigator: Navigator,
    context: CircuitContext,
  ): Presenter<*>? = when (screen) {
    is IslandScreens -> presenterOf { IslandPresenter(navigator) }
    is ShipScreen -> presenterOf { ShipPresenter(navigator) }
    is DockScreen -> presenterOf { DockPresenter(navigator) }
    else -> null
  }
}

class MainUiFactory : Ui.Factory {
  override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
    is IslandScreens -> ui<IslandScreens.State> { state, _ -> IslandUi(state) }
    is ShipScreen -> ui<ShipScreen.State> { state, _ -> ShipUi(state) }
    is DockScreen -> ui<DockScreen.State> { state, _ -> DockUi(state) }
    else -> null
  }
}
