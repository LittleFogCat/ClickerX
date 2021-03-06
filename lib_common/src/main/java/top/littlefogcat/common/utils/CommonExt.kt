package top.littlefogcat.common.utils

import android.view.MotionEvent
import android.view.ViewGroup

inline fun <T> T?.invokeIfNotNull(action: () -> T): T = this ?: action.invoke()

val Any.TAG get() = javaClass.simpleName

fun ViewGroup.MarginLayoutParams.setTopMargin(m: Int) {
    topMargin = m
}

fun ViewGroup.MarginLayoutParams.getTopMargin(): Int {
    return topMargin
}

fun MotionEvent.string(): String? {
    return MotionEvent.actionToString(action)
}

val MotionEvent.string: String
    get() = MotionEvent.actionToString(action)
