package top.littlefogcat.clickerx.base.ui

import android.app.KeyguardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
import android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
import com.alibaba.android.arouter.facade.annotation.Route
import top.littlefogcat.clickerx.base.base.BaseActivity
import top.littlefogcat.clickerx.base.constants.RouteConstants
import top.littlefogcat.common.R

@Route(path = RouteConstants.ROUTE_ACTIVITY_DUMMY)
class DummyActivity : BaseActivity() {
    companion object {
        const val TAG = "DummyActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val km = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
            km.requestDismissKeyguard(this, null)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.addFlags(FLAG_TURN_SCREEN_ON or FLAG_SHOW_WHEN_LOCKED)
            val keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        }
        setContentView(R.layout.activity_dummy)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ")
    }
}