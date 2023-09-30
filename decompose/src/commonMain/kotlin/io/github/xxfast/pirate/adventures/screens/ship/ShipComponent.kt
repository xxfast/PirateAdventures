package io.github.xxfast.pirate.adventures.screens.ship

import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import io.github.xxfast.pirate.adventures.screens.ship.ShipComponent.Config.CrowsNest
import io.github.xxfast.pirate.adventures.screens.ship.ShipComponent.Config.Deck
import io.github.xxfast.pirate.adventures.screens.ship.ShipComponent.Config.Gangway
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.utils.slide

class ShipComponent(
  componentContext: ComponentContext,
  val onNext: () -> Unit,
  val onPrevious: () -> Unit,
) : ComponentContext by componentContext {
  val navigation: StackNavigation<Config> = StackNavigation()

  val childStack: Value<ChildStack<Config, ComponentContext>> = childStack(
    source = navigation,
    initialConfiguration = Gangway,
    handleBackButton = true,
    childFactory = { configuration, componentContext ->
      when(configuration){
        Gangway -> GangwayComponent(componentContext) { navigation.push(Deck) }

        Deck -> DeckComponent(
          componentContext = componentContext,
          onNext = { navigation.push(CrowsNest) },
          onPrevious = { navigation.pop() }
        )

        CrowsNest ->  CrowsNestComponent(componentContext = componentContext) { navigation.pop() }
      }
    }
  )

  @Parcelize
  sealed class Config : Parcelable {
    data object Gangway : Config()
    data object Deck : Config()
    data object CrowsNest : Config()
  }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun ShipUi(component: ShipComponent) {
  Slide(
    onNext = component.onNext,
    onPrevious = component.onPrevious,
  ) {
    Children(
      stack = component.childStack,
      animation = predictiveBackAnimation(
        backHandler = component.backHandler,
        onBack = component.navigation::pop,
        animation = stackAnimation(slide(orientation = Vertical, inverted = true))
      )
    ) {
      when(val instance: ComponentContext = it.instance){
        is GangwayComponent -> GangwayUi(instance)
        is DeckComponent -> DeckUi(instance)
        is CrowsNestComponent -> CrowsNestUi(instance)
      }
    }
  }
}
