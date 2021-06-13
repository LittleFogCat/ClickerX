package top.littlefogcat.clickerx.recorder

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * 全局悬浮窗
 * @Author：littlefogcat
 * @Date：2020/11/11-0:28
 * @Email：littlefogcat@foxmail.com
 */

class GlobalFloatingWindow(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    companion object {
        const val TAG = "DragView"
        fun enableLog() = L.enable()
        fun disableLog() = L.disable()
        fun show(context: Context, layoutId: Int): GlobalFloatingWindow {
            val gfw = LayoutInflater.from(context).inflate(layoutId, null, false) as GlobalFloatingWindow
            gfw.show()
            return gfw
        }
    }

    private val delegate = MotionEventDelegate(this)
    private val windowManager: WindowManager
        get() = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent) = delegate.onMotionEvent(event)

    /**
     * 调用show()将
     */
    fun show() {
        if (parent != null) throw IllegalStateException("Already has parent")
        windowManager.addView(this, LP().apply {
            width = LP.WRAP_CONTENT
            height = LP.WRAP_CONTENT
            type = if (VERSION.SDK_INT >= VERSION_CODES.O) LP.TYPE_APPLICATION_OVERLAY else LP.TYPE_PHONE
            flags = LP.FLAG_NOT_TOUCH_MODAL or
                    LP.FLAG_NOT_FOCUSABLE or
                    LP.FLAG_LAYOUT_NO_LIMITS // 允许窗口移到屏幕外
            format = PixelFormat.RGBA_8888 // 允许透明背景
        })
    }

    fun dismiss() {
        windowManager.removeView(this)
    }

    fun reset() {
        delegate.reset()
    }
}