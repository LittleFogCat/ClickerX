package top.littlefogcat.component

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager

/**
 * 委托触摸事件
 */
class MotionEventDelegate(private val view: View) {

    private val wm = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val lp get() = view.layoutParams as WindowManager.LayoutParams
    private var moving = false

    private var srcRawX = 0
    private var srcRawY = 0
    private var srcLpX = 0
    private var srcLpY = 0

    fun onMotionEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                srcRawX = event.rawX.toInt()
                srcRawY = event.rawY.toInt()
                srcLpX = lp.x
                srcLpY = lp.y
                view.isPressed = true
                L.d(GlobalFloatingWindow.TAG, "Down: ($srcRawX, $srcRawY), ($srcLpX, $srcLpY)")
            }
            MotionEvent.ACTION_MOVE -> {
                if (!moving) moving = true
                L.v(GlobalFloatingWindow.TAG, "Move: ${event.rawX}, ${event.rawY}")
                val dx = event.rawX.toInt() - srcRawX
                val dy = event.rawY.toInt() - srcRawY
                lp.x = srcLpX + dx
                lp.y = srcLpY + dy
                wm.updateViewLayout(view, lp)
            }
            MotionEvent.ACTION_UP -> {
                L.d(GlobalFloatingWindow.TAG, "Up: (${event.x}, ${event.y})")
                if (moving) moving = false
                else {
                    L.d(GlobalFloatingWindow.TAG, "onMotionEvent: perform CLICK")
                    view.performClick()
                }
                view.isPressed = false
            }
        }

        return true
    }

    private fun updatePosition() {
        wm.updateViewLayout(view, lp)
    }

    internal fun reset() {
        lp.x = 0
        lp.y = 0
        updatePosition()
    }
}