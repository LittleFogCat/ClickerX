package top.littlefogcat.clickerx.recorder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog

const val TAG = "GlobalFloatingWindow"
typealias LP = WindowManager.LayoutParams

/**
 * 显示悬浮窗
 */
fun Context.showGlobalFloatingWindow(layoutId: Int): View {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val rootView = LayoutInflater.from(this).inflate(layoutId, null)
    val layoutParams = WindowManager.LayoutParams().apply {
        width = WindowManager.LayoutParams.WRAP_CONTENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else WindowManager.LayoutParams.TYPE_PHONE
        flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        format = PixelFormat.RGBA_8888
    }
    windowManager.addView(rootView, layoutParams)
    return rootView
}

fun Context.checkFloatingWindowPermission() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
        || Settings.canDrawOverlays(this)

/**
 * 检查悬浮窗权限。
 *
 * 可选参数[actionIfNoPermission]：如果检查到没有悬浮窗权限的话，那么[actionIfNoPermission]将被执行。
 * 如果没有设置[actionIfNoPermission]，默认弹出跳转到悬浮窗设置的对话框。
 */
fun Activity.ensureFloatingPermission(
    actionIfNoPermission: (() -> Unit)? = null
) {
    if (checkFloatingWindowPermission()) return
    if (actionIfNoPermission != null) actionIfNoPermission()
    else {
        AlertDialog.Builder(this)
            .setTitle("需要悬浮窗权限")
            .setMessage("点击确定跳转到权限设置")
            .setPositiveButton("确定") { _, _ ->
                val intent = Intent().apply {
                    action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
                    data = Uri.parse("package:$packageName")
                }
                startActivityForResult(intent, 0)
            }
            .show()
    }
}