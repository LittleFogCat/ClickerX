package top.littlefogcat.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import top.littlefogcat.base.utils.L

/**
 * @Author：littlefogcat
 * @Date：2020/12/18-16:44
 * @Email：littlefogcat@foxmail.com
 */
class ClickerService : AccessibilityService() {
    companion object {
        const val TAG = "ClickerService"
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        L.v(TAG, "onAccessibilityEvent: $event")
        when (event.eventType) {
        }
    }

    override fun onInterrupt() {

    }

    fun runTask(commands: List<SimuAction>) {

    }
}