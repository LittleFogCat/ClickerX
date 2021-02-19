package top.littlefogcat.clickerx

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import top.littlefogcat.clickerx.accessibility.ClickerXAccessibilityService
import top.littlefogcat.clickerx.main.MainActivity
import top.littlefogcat.clickerx.utils.isAccessibilitySettingsOn
import top.littlefogcat.clickerx.utils.launchActivity
import top.littlefogcat.clickerx.utils.makeDialog

class SplashActivity : AppCompatActivity() {
    private lateinit var mDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
//        LuaManager.init()
        checkAccessibilityPermission()
    }

    private fun checkAccessibilityPermission() {
        // 检查辅助功能是否开启
        if (!(BuildConfig.DEBUG || isAccessibilitySettingsOn(ClickerXAccessibilityService::class.java))) {
            mDialog = makeDialog(
                getString(R.string.enable_accessibility_dialog_title),
                getString(R.string.enable_accessibility_dialog_content),
                getString(R.string.confirm),
                DialogInterface.OnClickListener { _, _ -> startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) },
                false
            )
            mDialog.show()
        } else {
            launchActivity(MainActivity::class.java)
            finish()
        }
    }

}