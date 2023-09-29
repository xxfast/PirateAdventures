package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter
import io.github.xxfast.pirate.adventures.shared.components.Ocean
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreens.CrowsNest
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreens.Deck
import io.github.xxfast.pirate.adventures.screens.ship.ShipScreens.Gangway
import io.github.xxfast.pirate.adventures.shared.screens.ship.CrowsNestView
import io.github.xxfast.pirate.adventures.shared.screens.ship.DeckView
import io.github.xxfast.pirate.adventures.shared.screens.ship.GangwayView
import io.github.xxfast.pirate.adventures.utils.slide

@Parcelize
sealed class ShipScreens : Parcelable {
  data object Gangway : ShipScreens()
  data object Deck : ShipScreens()
  data object CrowsNest : ShipScreens()
}

@Composable
fun ShipScreen() {
  val router: Router<ShipScreens> = rememberRouter(ShipScreens::class) { listOf(Gangway) }

  RoutedContent(
    router = router,
    animation = stackAnimation(
      slide(
        animationSpec = tween(500),
        orientation = Vertical,
        inverted = true
      )
    )
  ) { screen ->
    when (screen) {
      Gangway -> Slide(
        onNext = { router.push(Deck) },
        orientation = Vertical,
        inverted = true,
      ) {
        GangwayView()
      }

      Deck -> Slide(
        onNext = { router.push(CrowsNest) },
        onPrevious = { router.pop() },
        orientation = Vertical,
        inverted = true,
      ) {
        DeckView()
      }

      CrowsNest -> Slide(
        onPrevious = { router.pop() },
        orientation = Vertical,
        inverted = true,
      ) {
        CrowsNestView()
      }
    }
  }
}

@Composable
fun GangwayScreen() {

}
