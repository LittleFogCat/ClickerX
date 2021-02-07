package top.littlefogcat.clickerx.accessibility

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import java.util.*
import kotlin.concurrent.timerTask

/**
 * 作者：littlefogcat
 * 创建日期：2020/8/12-19:07
 * Email：littlefogcat@foxmail.com
 */
fun AccessibilityService.startTraverseTask(TAG: String) {

    /**
     * 遍历所有子结点
     *
     * @param root
     */
    fun traverse(root: AccessibilityNodeInfo?) {
        if (root == null) {
            return
        }
        val c = root.childCount
        val log =
            StringBuilder("traverse: id = " + root.viewIdResourceName + ", class = " + root.className + ", clickable = " + root.isClickable)
        if (root.text != null) {
            log.append(", text = ${root.text}")
        }
        if (c != 0) {
            log.append(", children = [")
            for (i in 0 until c) {
                val child = root.getChild(i)
                log.append(child.viewIdResourceName).append(", ")
            }
            log.delete(log.length - 2, log.length)
            log.append("]")
        }
        Log.d(TAG, "traverse: $log")
        for (i in 0 until c) {
            val child = root.getChild(i)
            traverse(child)
        }
    }

    val timer = Timer()
    val task = timerTask {
        traverse(rootInActiveWindow)
    }
    timer.schedule(task, 3000, 10000)
}