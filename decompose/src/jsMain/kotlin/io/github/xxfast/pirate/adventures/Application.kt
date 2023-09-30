package io.github.xxfast.pirate.adventures

import androidx.compose.material3.MaterialTheme
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import io.github.xxfast.pirate.adventures.screens.MainComponent
import io.github.xxfast.pirate.adventures.screens.MainUi
import io.github.xxfast.pirate.adventures.utils.BrowserViewportWindow
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.Document

fun main() {
  val lifecycleRegistry = LifecycleRegistry()
  val rootComponentContext = DefaultComponentContext(lifecycleRegistry)
  val mainComponent = MainComponent(rootComponentContext)
  lifecycleRegistry.attachToDocument()
  onWasmReady {
    BrowserViewportWindow("Pirate Adventures 🏴‍☠️") {
      MaterialTheme {
        MainUi(mainComponent)
      }
    }
  }
}

// Attaches the LifecycleRegistry to the document
private fun LifecycleRegistry.attachToDocument() {
  fun onVisibilityChanged() = if (document.visibilityState == "visible") resume() else stop()
  onVisibilityChanged()
  document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

private val Document.visibilityState: String
  get() = asDynamic().visibilityState.unsafeCast<String>()
