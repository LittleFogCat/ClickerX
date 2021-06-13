package top.littlefogcat.clickerx.recorder

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import top.littlefogcat.clickerx.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalFloatingWindow.disableLog()
        ensurePermission()
    }

    private fun showFloatingWindow() {
        Toast.makeText(this, "show", Toast.LENGTH_SHORT).show()
        startService(Intent(this, MyService::class.java))
        finish()
    }

    private fun ensurePermission() {
        if (checkFloatingWindowPermission()) {
            showFloatingWindow()
        } else {
            val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (checkFloatingWindowPermission()) {
                    showFloatingWindow()
                } else {
                    Toast.makeText(this, "获取悬浮窗权限失败，请手动打开", Toast.LENGTH_SHORT).show()
                }
            }
            launcher.launch(Intent().apply {
                action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
                data = Uri.parse("package:$packageName")
            })
        }
    }

    /**
     * 是否开启悬浮窗权限
     */
    private fun checkFloatingWindowPermission() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            || Settings.canDrawOverlays(this)

    fun onViewClicked(view: View) {}

}
