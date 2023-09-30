package io.github.xxfast.pirate.adventures

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIResponder
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIScreen
import platform.UIKit.UIWindow

@OptIn(BetaInteropApi::class)
class AppDelegate @OverrideInit constructor() : UIResponder(), UIApplicationDelegateProtocol {
  companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

  private val lifecycle: LifecycleRegistry = LifecycleRegistry()
  private val rootComponentContext = DefaultComponentContext(lifecycle)

  private var _window: UIWindow? = null
  override fun window() = _window
  override fun setWindow(window: UIWindow?) {
    _window = window
  }

  @OptIn(ExperimentalForeignApi::class)
  override fun application(
    application: UIApplication,
    didFinishLaunchingWithOptions: Map<Any?, *>?
  ): Boolean {
    window = UIWindow(frame = UIScreen.mainScreen.bounds)
    window!!.rootViewController = MainUIViewController(rootComponentContext)
    window!!.makeKeyAndVisible()
    return true
  }

  override fun applicationDidBecomeActive(application: UIApplication) {
    lifecycle.resume()
  }

  override fun applicationWillResignActive(application: UIApplication) {
    lifecycle.stop()
  }

  override fun applicationWillTerminate(application: UIApplication) {
    lifecycle.destroy()
  }
}
