package top.littlefogcat.clickerx.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import top.littlefogcat.clickerx.BuildConfig

class ClickerXAccessibilityService : AccessibilityService() {
    companion object {
        const val TAG = "ClickerXAccessibility"
    }

    init {
        if (BuildConfig.DEBUG) {
            startTraverseTask(TAG)
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

    }

    override fun onInterrupt() {

    }


}
