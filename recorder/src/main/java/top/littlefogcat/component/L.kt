package top.littlefogcat.component

import android.util.Log

/**
 * @Author：littlefogcat
 * @Date：2020/11/11-0:34
 * @Email：littlefogcat@foxmail.com
 */
internal object L {
    private var enabled = false

    fun enable() {
        enabled = true
    }

    fun disable() {
        enabled = false
    }

    fun v(tag: String, msg: String) {
        if (enabled) Log.v(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (enabled) Log.d(tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (enabled) Log.i(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (enabled) Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (enabled) Log.e(tag, msg)
    }
}