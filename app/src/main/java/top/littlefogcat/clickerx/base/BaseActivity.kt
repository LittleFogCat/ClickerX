package top.littlefogcat.clickerx.base

import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity基类。
 *
 * 实现了Fragment分发事件。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseActivity : AppCompatActivity() {
    protected val TAG by lazy {
        javaClass.simpleName
    }

    /**
     * Fragment实现[TouchEventHandler]并调用[addTouchEventHandler]以分发触摸事件
     */
    private val touchEventHandlers = mutableListOf<TouchEventHandler>()

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        var handled = false
        for (handler in touchEventHandlers) {
            if (handler is BaseFragment<*> && !handler.isResumed) {
                continue
            }
            handled = handler.dispatchTouchEvent(event) || handled
        }
        return handled || super.dispatchTouchEvent(event)
    }

    fun addTouchEventHandler(handler: TouchEventHandler) {
        touchEventHandlers.add(handler)
    }

    fun removeTouchEventHandler(handler: TouchEventHandler) {
        touchEventHandlers.remove(handler)
    }

    fun removeAllTouchEventHandler() {
        touchEventHandlers.clear()
    }
}
