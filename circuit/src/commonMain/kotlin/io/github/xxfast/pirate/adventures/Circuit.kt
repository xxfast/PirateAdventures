package io.github.xxfast.pirate.adventures

import com.slack.circuit.foundation.Circuit
import io.github.xxfast.pirate.adventures.screens.MainPresenterFactory
import io.github.xxfast.pirate.adventures.screens.MainUiFactory
import io.github.xxfast.pirate.adventures.screens.dock.DockPresenterFactory
import io.github.xxfast.pirate.adventures.screens.dock.DockUiFactory
import io.github.xxfast.pirate.adventures.screens.ship.ShipPresenterFactory
import io.github.xxfast.pirate.adventures.screens.ship.ShipUiFactory

val circuit: Circuit = Circuit.Builder()
  .addUiFactory(MainUiFactory(), ShipUiFactory(), DockUiFactory())
  .addPresenterFactory(MainPresenterFactory(), ShipPresenterFactory(), DockPresenterFactory())
  .build()
