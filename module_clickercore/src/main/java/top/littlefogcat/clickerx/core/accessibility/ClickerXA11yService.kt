package top.littlefogcat.clickerx.core.accessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.app.Notification
import android.content.Intent
import android.graphics.Path
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.SparseArray
import android.view.accessibility.AccessibilityEvent
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.os.bundleOf
import androidx.core.util.forEach
import top.littlefogcat.clickerx.core.Core
import top.littlefogcat.clickerx.core.trigger.NotificationTrigger
import top.littlefogcat.clickerx.common.utils.ScreenUtil
import top.littlefogcat.clickerx.common.utils.performClick
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 辅助功能的主Service。
 */
class ClickerXA11yService : AccessibilityService() {
    companion object {
        const val TAG = "ClickerXAccessibility"
        var instance: ClickerXA11yService? = null
    }

    private val handler = Handler(Looper.getMainLooper())

    private val triggers = TriggerInfo()

    class TriggerInfo {
        val notificationTriggers = CopyOnWriteArrayList<NotificationTrigger>()
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "onServiceConnected: Core.service bind")
        Core.setService(this)
        instance = this
        ScreenUtil.init(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        when (event.eventType) {
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> {
                onNotificationChanged(event)
            }
            else -> {
                Log.v(TAG, "onAccessibilityEvent: ${event.actionToString()}")
            }
        }
    }

    /**
     * 当通知栏发生变化
     */
    private fun onNotificationChanged(event: AccessibilityEvent) {
        val notification = (event.parcelableData ?: return) as Notification
        Log.v(TAG, "onNotificationChanged: $notification, event: $event.type")
        val title = notification.extras[Notification.EXTRA_TITLE]
        val content = notification.extras[Notification.EXTRA_TEXT]
        val pkgName = event.packageName
        for (trigger in triggers.notificationTriggers) {
            val p = trigger.params
            if (p.containsKey("title") && p["title"] != title ||
                p.containsKey("content") && p["content"] != content ||
                p.containsKey("pkgName") && p["pkgName"] != pkgName
            ) continue
            val notificationInfo = NotificationInfo(notification)
            val luaRet = notificationInfo.toLuaValue()
            trigger.onTrigger(luaRet)
        }
    }

    // ------------- 功能区 --------------

    fun clickBy(args: Map<*, *>) {
        traverse(rootInActiveWindow) {
            if (args.containsKey("text") && args["text"] != it.text ||
                args.containsKey("desc") && args["desc"] != it.contentDescription ||
                args.containsKey("id") && args["id"] != it.viewIdResourceName ||
                args.containsKey("contentDescription") && args["contentDescription"] != it.contentDescription
            ) {
                false
            } else {
                it.performClick()
                true
            }
        }
    }

    fun clickById(id: String) {
        traverse(rootInActiveWindow) {
            if (it.isClickable && it.viewIdResourceName == id) {
                it.performClick()
            } else false
        }
    }

    fun clickByText(text: String) {
        traverse(rootInActiveWindow) {
            if (it.isClickable && it.text == text) {
                it.performClick()
            } else false
        }
    }

    fun clickByText1(text: String) {
        traverse(rootInActiveWindow) {
            if (it.text == text) {
                var view = it
                while (view.parent != null && !view.isClickable) view = view.parent
                if (view.isClickable) {
                    view.performClick()
                    return@traverse true
                }
            }
            false
        }
    }

    /**
     * 点击屏幕位置[x, y]的点
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickAt(x: Float, y: Float) {
        GestureDescription.Builder()
            .addStroke(
                GestureDescription.StrokeDescription(
                    Path().apply { moveTo(x, y) },
                    0L,
                    10L
                )
            )
            .build()
            .let { dispatchGesture(it, null, null) }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun swipe(x1: Float, y1: Float, x2: Float, y2: Float, duration: Long) {
        GestureDescription.Builder()
            .addStroke(
                GestureDescription.StrokeDescription(
                    Path().apply {
                        moveTo(x1, y1)
                        lineTo(x2, y2)
                    },
                    0L,
                    duration
                )
            )
            .build()
            .let {
                dispatchGesture(it, object : GestureResultCallback() {
                    override fun onCompleted(gestureDescription: GestureDescription?) {
                        Log.d(TAG, "onCompleted: ")
                    }

                    override fun onCancelled(gestureDescription: GestureDescription?) {
                        Log.d(TAG, "onCancelled: ")
                    }
                }, null)
            }
    }

    fun input(text: String) {
        val args = bundleOf(Pair(A11yNode.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text))
        val focus = findFocus(A11yNode.FOCUS_INPUT)
        if (focus == null) {
            traverse(rootInActiveWindow) {
                if (it.className == AppCompatEditText::class.java.name || it.className == EditText::class.java.name) {
                    it.performClick()
                    it.performAction(A11yNode.ACTION_SET_TEXT, args)
                }
                false
            }
        } else {
            focus.performAction(A11yNode.ACTION_SET_TEXT, args)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun unlock(pwd: String) {
        Log.d(TAG, "unlock: $pwd")
        beginTransaction()
            .schedule(1000) { performGlobalAction(GLOBAL_ACTION_HOME) }
            .schedule(3000) { swipe(400f, 1500f, 500f, 900f, 220) }
            .apply {
                pwd.forEach { schedule(1000) { clickByText1(it.toString()) } }
            }
            .commit()
    }

    fun addNotificationTrigger(trigger: NotificationTrigger) {
        triggers.notificationTriggers.add(trigger)
    }

    fun removeTrigger(id: Long) {
        triggers.notificationTriggers.removeAll { it.scriptId == id }
    }

    // --------------------------------

    override fun onInterrupt() {
        Log.i(TAG, "onInterrupt: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Core.setService(null)
        return super.onUnbind(intent)
    }

    private fun AccessibilityEvent.actionToString(): String {
        return AccessibilityEvent.eventTypeToString(eventType)
    }

    /**
     * 遍历根节点[root]，并对每个节点执行[action]。
     * [action]的返回值为Boolean类型，如果要继续遍历则返回false，如果已经找到执行对象不需要继续执行了，就返回true。
     *
     * @return 不需要继续遍历返回true，否则返回false
     */
    internal fun traverse(root: A11yNode?, action: (A11yNode) -> Boolean): Boolean {
        if (root == null) return false
        if (action(root)) return true
        val c = root.childCount
        for (i in 0 until c) {
            val child = root.getChild(i)
            if (traverse(child, action)) return true
        }
        return false
    }

    private fun beginTransaction(): Transaction {
        return Transaction()
    }

    inner class Transaction {
        val actions = SparseArray<() -> Unit>()
        var totalDelay = 0

        fun schedule(delay: Int = 0, action: () -> Unit): Transaction {
            totalDelay += delay
            actions.append(totalDelay, action)
            return this
        }

        fun commit() {
            actions.forEach { delay, action ->
                handler.postDelayed(action, delay.toLong())
            }
        }
    }

}
