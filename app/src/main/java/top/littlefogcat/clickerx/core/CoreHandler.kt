package top.littlefogcat.clickerx.core

import android.os.*
import top.littlefogcat.clickerx.utils.showToast

/**
 * Lua脚本中的每一个操作，对应的Runnable对象都会传递到这个类里，并通过[innerH]在设置的延时之后执行。
 *
 * 不立即执行主要是为了处理延时任务，即Lua脚本中的`delay`函数。
 *
 * todo 考虑使用Transaction，并标记调用者
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object CoreHandler {
//    const val MSG_LAUNCH_APP = 0

    private val thread = HandlerThread("core-handler-thread").apply { start() }
    private val innerH by lazy { Handler(thread.looper) }
    var defaultDelay = -1L

    /**
     * 超时时间。当调用[delay]方法，则设置超时时间；所有的[Runnable]都在之后进行，类似于[Thread.sleep]。
     * 实现Lua中的delay()函数。
     */
    private var expire = 0L

    /**
     * 预定一个任务[r]执行。根据[expire]决定[r]立即执行或是延迟执行，并且对[r]进行try-catch处理。
     *
     * [id]为脚本的id，这个是为了在运行出错之后移除所有预设的任务。
     */
    fun post(id: Long, r: () -> Unit) {
        val wrapperRunnable = {
            try {
                r()
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is LuaException) {
                    showToast("$e")
                } else {
                    showToast("Error: " + e.message.toString())
                }
                innerH.removeCallbacksAndMessages(id)
            }
        }
        val ct = SystemClock.uptimeMillis()
        if (ct > expire) {
            innerH.postAtTime(wrapperRunnable, id, ct + 1)
        } else {
            innerH.postAtTime(wrapperRunnable, id, expire)
        }
        if (defaultDelay > 0) {
            delay(defaultDelay)
        }
    }

    /**
     * 根据[expire]决定[r]立即执行或是延迟执行。
     */
    fun post(r: () -> Unit) {
        post(Core.runningScript?.id ?: 0, r)
//        val ct = SystemClock.uptimeMillis()
//        if (ct > expire) {
//            innerH.postAtTime(r, ct + 1)
//        } else {
//            innerH.postAtTime(r, expire)
//        }
//        if (defaultDelay > 0) {
//            delay(defaultDelay)
//        }
    }

    fun delay(millis: Long) {
        if (expire < SystemClock.uptimeMillis()) {
            expire = SystemClock.uptimeMillis() + millis
        } else {
            expire += millis
        }
    }

    fun removeCallbacks(who: Long?) {
        innerH.removeCallbacksAndMessages(who)
    }
}