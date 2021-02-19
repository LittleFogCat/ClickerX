package top.littlefogcat.clickerx.utils

inline fun <T> T?.invokeIfNotNull(action: () -> T): T = this ?: action.invoke()

val Any.TAG get() = javaClass.simpleName