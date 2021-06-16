package top.littlefogcat.clickerx.base.base

import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.Postcard
import top.littlefogcat.clickerx.base.R

/**
 * Activity基类。
 *
 * 实现了Fragment分发事件。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseActivity : AppCompatActivity() {
    @Suppress("PropertyName")
    protected val TAG: String by lazy {
        javaClass.simpleName
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 覆写返回动画
        overridePendingTransition(R.anim.rtl_back_enter, R.anim.rtl_back_exit)
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
