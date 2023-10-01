package io.github.xxfast.pirate.adventures.screens

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import io.github.xxfast.pirate.adventures.screens.MainComponent.Config.Dock
import io.github.xxfast.pirate.adventures.screens.MainComponent.Config.Island
import io.github.xxfast.pirate.adventures.screens.MainComponent.Config.Ship
import io.github.xxfast.pirate.adventures.screens.dock.DockComponent
import io.github.xxfast.pirate.adventures.screens.dock.DockUi
import io.github.xxfast.pirate.adventures.screens.ship.ShipComponent
import io.github.xxfast.pirate.adventures.screens.ship.ShipUi
import io.github.xxfast.pirate.adventures.shared.components.Slide
import io.github.xxfast.pirate.adventures.shared.screens.island.IslandView

class MainComponent(
  componentContext: ComponentContext
): ComponentContext by componentContext {
  val navigation: StackNavigation<Config> = StackNavigation()

  val childStack: Value<ChildStack<Config, Any>> = childStack(
    source = navigation,
    initialConfiguration = Island,
    handleBackButton = true,
    childFactory = { configuration, componentContext ->
      when(configuration){
        Island -> Unit

        Ship -> ShipComponent(
          componentContext = componentContext,
          onNext = { navigation.push(Dock) },
          onPrevious = { navigation.pop() }
        )

        Dock ->  DockComponent(componentContext = componentContext) { navigation.pop() }
      }
    }
  )

  @Parcelize
  sealed class Config : Parcelable {
    data object Island : Config()
    data object Ship : Config()
    data object Dock : Config()
  }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainUi(component: MainComponent) {
  Children(
    stack = component.childStack,
    animation = predictiveBackAnimation(
      backHandler = component.backHandler,
      onBack = component.navigation::pop,
      animation = stackAnimation(slide())
    )
  ) {
    when(val instance = it.instance){
      is Unit -> Slide(
        onNext = { component.navigation.push(Ship) }
      ) {
        IslandView()
      }

      is ShipComponent -> ShipUi(instance)
      is DockComponent -> DockUi(instance)
    }
  }
}
