package top.littlefogcat.clickerx.base

import android.view.MotionEvent

interface TouchEventHandler {
    fun onTouchEvent(event: MotionEvent): Boolean
}