package top.littlefogcat.clickerx.utils

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.content.Context
import android.graphics.Path
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import top.littlefogcat.clickerx.core.accessibility.ClickerXA11yService
import java.util.*

private const val TAG = "AccessibilityUtils"

/**
 * 检测是否本辅助功能开启
 */
fun Context.isAccessibilitySettingsOn(clazz: Class<out AccessibilityService>): Boolean {
    val className = clazz.simpleName.toLowerCase(Locale.getDefault())
    try {
        val accessibilityEnabled = Settings.Secure.getInt(
            applicationContext.contentResolver,
            Settings.Secure.ACCESSIBILITY_ENABLED
        )
        if (accessibilityEnabled == 1) {
            val settingValue = Settings.Secure.getString(
                applicationContext.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            ).toLowerCase(Locale.getDefault())
            return settingValue.contains(className)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}
//
///**
// * 检查辅助功能是否开启
// */
//fun Context.isAccessibilitySettingsOn(): Boolean {
//    val clazz = ClickerXA11yService::class.java
//    val className = clazz.simpleName.toLowerCase(Locale.getDefault())
//    try {
//        val accessibilityEnabled = Settings.Secure.getInt(
//            applicationContext.contentResolver,
//            Settings.Secure.ACCESSIBILITY_ENABLED
//        )
//        if (accessibilityEnabled == 1) {
//            val settingValue = Settings.Secure.getString(
//                applicationContext.contentResolver,
//                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
//            ).toLowerCase(Locale.getDefault())
//            return settingValue.contains(className)
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    return false
//}

/**
 * 查找id和text都满足的node。返回第一个同时满足的node。
 * 如果text留空或null，那就只查找id。
 * 如果id留空或null，那就只查找text。
 */
fun findAccessibilityNodeInfoByIdAndText(root: AccessibilityNodeInfo, id: String? = null, text: String? = null): AccessibilityNodeInfo? {
    if (id == null && text == null) throw RuntimeException("It's impossible to find a node when id and text are both null.")

    val nodeList: List<AccessibilityNodeInfo>? = if (id != null) {
        root.findAccessibilityNodeInfosByViewId(id)
    } else {
        root.findAccessibilityNodeInfosByText(text)
    }

    if (nodeList == null || nodeList.isEmpty()) return null

    if (id == null || text == null) return nodeList[0]
    for (node in nodeList) {
        if (node.text == text) return node
    }
    return null
}

/**
 * 点击
 */
fun performClickAccessibilityButton(root: AccessibilityNodeInfo, id: String?, text: String? = null) {
//    Logger.v(TAG, "performClickAccessibilityButton: $id, $text")
    val btn = findAccessibilityNodeInfoByIdAndText(root, id, text)
    if (btn != null && btn.isClickable) {
        btn.performClick()
    }
}

/**
 * 遍历Node并执行[action]
 */
fun traverse(root: AccessibilityNodeInfo?, action: (Any) -> Unit) {
    if (root == null) {
        return
    }
    action(root)
    if (root.childCount == 0) {
        return
    }
    for (i in 0..root.childCount) {
        val child = root.getChild(i)
        traverse(child, action)
    }

}

fun AccessibilityNodeInfo.performClick() = performAction(AccessibilityNodeInfo.ACTION_CLICK)

fun AccessibilityService.dispatchGesture(startX: Int, endX: Int, startY: Int, endY: Int) {
    Log.d(TAG, "dispatchGesture: 1")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Log.d(TAG, "dispatchGesture: 2")
        val path = Path()
        path.moveTo(startX.toFloat(), startY.toFloat())
        path.lineTo(endX.toFloat(), endY.toFloat())
        val desc = GestureDescription.StrokeDescription(path, 0, 500)
        dispatchGesture(GestureDescription.Builder().addStroke(desc).build(), null, null)
    } else {
        TODO("VERSION.SDK_INT < N")
    }
}
