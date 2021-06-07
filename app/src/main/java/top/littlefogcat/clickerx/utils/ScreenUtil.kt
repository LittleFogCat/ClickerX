package top.littlefogcat.clickerx.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object ScreenUtil {
    const val TAG = "ScreenUtil"
    private var isInitialized = false
    private var width = 1080
    private var height = 2160

    fun init(context: Context) {
        if (context is Activity) {
            // Activity获取屏幕尺寸为高优先级，每次init都更新
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(metrics)
            width = metrics.widthPixels
            height = metrics.heightPixels
        } else if (!isInitialized) {
            // Activity以外获取metrics为低优先级
            // 根据未经考证的说法，在Activity外获取context.resources.displayMetrics可能会得到不正确的结果。
            val metrics = context.resources.displayMetrics
            width = metrics.widthPixels
            height = metrics.heightPixels
        }
        isInitialized = true
        Log.d(TAG, "init: screen size = [$width, $height]")
    }
}