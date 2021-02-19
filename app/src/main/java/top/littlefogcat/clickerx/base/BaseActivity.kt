package top.littlefogcat.clickerx.base

import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import top.littlefogcat.clickerx.utils.replaceFragment

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:18
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseActivity : AppCompatActivity() {
    protected val TAG by lazy {
        javaClass.simpleName
    }

    private val touchEventHandlers = mutableListOf<TouchEventHandler>()

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        var handled = false
        for (handler in touchEventHandlers) {
            if (handler.onTouchEvent(ev)) {
                handled = true
            }
        }
        return handled || super.dispatchTouchEvent(ev)
    }

    fun addTouchEventHandler(handler: TouchEventHandler) {
        touchEventHandlers.add(handler)
    }

    fun removeTouchEventHandler(handler: TouchEventHandler) {
        touchEventHandlers.remove(handler)
    }

    fun removeAllTouchEventHandler(){
        touchEventHandlers.clear()
    }
}
