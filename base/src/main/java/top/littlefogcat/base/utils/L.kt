package top.littlefogcat.base.utils

import android.util.Log

/**
 * 日志工具类。
 *
 * @Author：littlefogcat
 * @Date：2020/12/23-23:03
 * @Email：littlefogcat@foxmail.com
 */
object L {
    private var enable = false

    fun enable() {
        enable = true
    }

    fun disable() {
        enable = false
    }

    fun v(TAG: String?, msg: String) {
        if (!enable) return
        Log.v(TAG, msg)
    }

    fun d(TAG: String?, msg: String) {
        if (!enable) return
        Log.d(TAG, msg)
    }

    fun i(TAG: String?, msg: String) {
        if (!enable) return
        Log.i(TAG, msg)
    }

    fun w(TAG: String?, msg: String) {
        if (!enable) return
        Log.w(TAG, msg)
    }

    fun e(TAG: String?, msg: String, tr: Throwable?) {
        if (!enable) return
        Log.e(TAG, msg, tr)
    }

    fun wtf(TAG: String?, msg: String, tr: Throwable?) {
        if (!enable) return
        Log.wtf(TAG, msg, tr)
    }
}