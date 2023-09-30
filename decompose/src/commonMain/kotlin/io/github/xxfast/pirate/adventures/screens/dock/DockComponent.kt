package io.github.xxfast.pirate.adventures.screens.dock

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
import io.github.xxfast.pirate.adventures.screens.dock.DockComponent.Config.Ship
import io.github.xxfast.pirate.adventures.screens.dock.DockComponent.Config.Wharf
import io.github.xxfast.pirate.adventures.screens.ship.CrowsNestComponent
import io.github.xxfast.pirate.adventures.screens.ship.CrowsNestUi
import io.github.xxfast.pirate.adventures.screens.ship.DeckComponent
import io.github.xxfast.pirate.adventures.screens.ship.DeckUi
import io.github.xxfast.pirate.adventures.screens.ship.GangwayComponent
import io.github.xxfast.pirate.adventures.screens.ship.GangwayUi
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.dock.ShipView
import io.github.xxfast.pirate.adventures.utils.slide

class DockComponent(
  componentContext: ComponentContext,
  val onPrevious: () -> Unit,
) : ComponentContext by componentContext {
  val navigation: StackNavigation<Config> = StackNavigation()

  val childStack: Value<ChildStack<Config, ComponentContext>> = childStack(
    source = navigation,
    initialConfiguration = Ship,
    handleBackButton = true,
    childFactory = { configuration, componentContext ->
      when(configuration){
        Ship -> ShipComponent(componentContext) { navigation.push(Wharf) }
        Wharf ->  WharfComponent(componentContext = componentContext) { navigation.pop() }
      }
    }
  )

  @Parcelize
  sealed class Config : Parcelable {
    data object Ship : Config()
    data object Wharf : Config()
  }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun DockUi(component: DockComponent) {
  Slide(
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
        is ShipComponent -> ShipUi(instance)
        is WharfComponent -> WharfUi(instance)
      }
    }
  }
}
