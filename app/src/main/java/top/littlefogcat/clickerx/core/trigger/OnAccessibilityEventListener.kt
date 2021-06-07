package top.littlefogcat.clickerx.core.trigger

import android.view.accessibility.AccessibilityEvent

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface OnAccessibilityEventListener {
    fun onEvent(event: AccessibilityEvent)
}