package io.github.xxfast.pirate.adventures

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.pirate.adventures.screens.MainScreen
import platform.UIKit.UIViewController

@OptIn(ExperimentalDecomposeApi::class)
fun Main(): UIViewController = ComposeUIViewController {
  val lifecycle = LifecycleRegistry()
  val backDispatcher = BackDispatcher()
  val rootComponentContext = RouterContext(lifecycle = lifecycle, backHandler = backDispatcher)

  CompositionLocalProvider(
    LocalRouterContext provides rootComponentContext,
  ) {
    MaterialTheme {
      PredictiveBackGestureOverlay(
        backDispatcher = backDispatcher, // Use the same BackDispatcher as above
        backIcon = { progress, _ ->
          PredictiveBackGestureIcon(
            imageVector = Icons.Default.ArrowBack,
            progress = progress,
          )
        },
        modifier = Modifier.fillMaxSize(),
      ) {
        MainScreen()
      }
    }
  }
}
