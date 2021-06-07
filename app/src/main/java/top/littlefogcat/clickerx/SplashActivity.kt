package top.littlefogcat.clickerx

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import top.littlefogcat.clickerx.core.accessibility.ClickerXA11yService
import top.littlefogcat.clickerx.main.MainActivity
import top.littlefogcat.clickerx.utils.ScreenUtil
import top.littlefogcat.clickerx.utils.isAccessibilitySettingsOn
import top.littlefogcat.clickerx.utils.launchActivity

class SplashActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SplashActivity"
    }

    private var mDialog: AlertDialog? = null
    private var ignoreAccessibilityPermission = false
    private var ignoreListenNotificationPermission = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onResume() {
        super.onResume()

        ScreenUtil.init(this)

        if (checkPermissions()) {
            jumpToMainActivity()
        }
    }

    /**
     * 检查权限。
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun checkPermissions(): Boolean {
        // 检查辅助功能是否开启
        return if (!ignoreAccessibilityPermission && !isAccessibilitySettingsOn(ClickerXA11yService::class.java)) {
            // 辅助功能权限未开启，跳转到设置
            requestAccessibilityPermission()
            false
        } else true
    }

    /**
     * 跳出对话框跳转到辅助功能设置
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun requestAccessibilityPermission() {
        mDialog = AlertDialog.Builder(this)
            .setTitle(R.string.enable_accessibility_dialog_title)
            .setMessage(R.string.enable_accessibility_dialog_content)
            .setCancelable(BuildConfig.DEBUG)
            .setPositiveButton(R.string.confirm) { _, _ ->
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                mDialog?.dismiss()
            }
            .setOnCancelListener {
                ignoreAccessibilityPermission = true
                jumpToMainActivity()
            }
            .show()
    }

    /**
     * 跳出对话框跳转到监听权限设置
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun requestListenNotificationPermission() {
        mDialog = AlertDialog.Builder(this)
            .setTitle("权限提示")
            .setMessage("功能需要开启监听通知权限。点击确定，找到本应用以启用。")
            .setCancelable(BuildConfig.DEBUG)
            .setPositiveButton("确定") { _, _ ->
                startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
                mDialog?.dismiss()
            }
            .setOnCancelListener {
                ignoreListenNotificationPermission = true
                jumpToMainActivity()
            }
            .show()
    }

    /**
     * 检查是否得到监听权限
     */
    private fun isListenNotificationPermissionGranted(): Boolean {
        val pkgName = packageName
        val flat = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        if (!flat.isNullOrEmpty()) {
            val names = flat.split(":")
            for (name in names) {
                val component = ComponentName.unflattenFromString(name)
                if (component != null && component.packageName == pkgName) {
                    // 开启权限了，跳转到MainActivity
                    return true
                }
            }
        }
        return false
    }

    /**
     * 跳转至主界面
     */
    private fun jumpToMainActivity() {
        launchActivity(MainActivity::class.java)
        mDialog?.dismiss()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDialog?.run {
            if (isShowing) dismiss()
            mDialog = null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        jumpToMainActivity()
    }

}
