package top.littlefogcat.clickerx.base

import android.view.MotionEvent

interface TouchEventHandler {
    /**
     * @see android.app.Activity.dispatchTouchEvent
     */
    fun dispatchTouchEvent(event: MotionEvent): Boolean
}