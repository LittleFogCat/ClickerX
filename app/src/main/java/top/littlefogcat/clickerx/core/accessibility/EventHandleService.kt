package top.littlefogcat.clickerx.core.accessibility

import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.MotionEvent

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class EventHandleService : InputMethodService() {
    companion object {
        const val TAG = "EventHandleService"
    }

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "onGenericMotionEvent: $event")
        return super.onGenericMotionEvent(event)
    }
}