package io.github.xxfast.pirate.adventures.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import io.github.xxfast.decompose.LocalComponentContext
import io.github.xxfast.pirate.adventures.screens.MainScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val rootComponentContext: DefaultComponentContext = defaultComponentContext()
    setContent {
      CompositionLocalProvider(
        LocalComponentContext provides rootComponentContext,
      ) {
        MyApplicationTheme {
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
          ) {
            MainScreen()
          }
        }
      }
    }
  }
}


@Preview
@Composable
fun DefaultPreview() {
  MyApplicationTheme {
    MainScreen()
  }
}
