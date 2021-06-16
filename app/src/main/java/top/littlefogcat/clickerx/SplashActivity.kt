package top.littlefogcat.clickerx

import android.content.Intent
import android.provider.Settings
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.base.base.BaseActivity
import top.littlefogcat.clickerx.base.constants.RouteConstants
import top.littlefogcat.clickerx.base.utils.asDefault
import top.littlefogcat.clickerx.core.accessibility.ClickerXA11yService
import top.littlefogcat.common.utils.AlertDialogCompat
import top.littlefogcat.common.utils.ScreenUtil
import top.littlefogcat.common.utils.isAccessibilitySettingsOn

class SplashActivity : BaseActivity() {
    private var ignoreAccessibilityPermission = false

    override fun onResume() {
        super.onResume()

        ScreenUtil.init(this)
        checkPermissionAndJump()
    }

    private fun checkPermissionAndJump() {
        if (checkPermissions()) {
            jumpToMainActivity()
        }
    }

    /**
     * 检查权限。
     */
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
    private fun requestAccessibilityPermission() {
        AlertDialogCompat.Builder(this)
            .setTitle(R.string.enable_accessibility_dialog_title)
            .setMessage(R.string.enable_accessibility_dialog_content)
            .setCancelable(BuildConfig.DEBUG)
            .setPositiveButton(R.string.confirm) { _, _ ->
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
            .setOnCancelListener {
                ignoreAccessibilityPermission = true
                checkPermissionAndJump()
            }
            .show()
    }

    /**
     * 跳转至主界面
     */
    private fun jumpToMainActivity() {
        ARouter.getInstance()
            .build(RouteConstants.ROUTE_ACTIVITY_MAIN)
            .asDefault()
            .navigation(this)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        checkPermissionAndJump()
    }

}
