package top.littlefogcat.clickerx.core.accessibility

import android.app.Notification
import android.util.Log
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.jse.CoerceJavaToLua

/**
 * 用于Lua回调中返回Notification
 */
class NotificationInfo(private val source: Notification) {
    companion object {
        const val TAG = "NotificationInfo"
    }

    fun getTitle(): String? {
        return source.extras[Notification.EXTRA_TITLE] as String?
    }

    fun getContent(): String? {
        return source.extras[Notification.EXTRA_TEXT] as String?
    }

    fun click() {
        Log.d(TAG, "click: ")
        source.contentIntent.send()
    }

    fun toLuaValue(): LuaValue {
        return CoerceJavaToLua.coerce(this)
    }
}