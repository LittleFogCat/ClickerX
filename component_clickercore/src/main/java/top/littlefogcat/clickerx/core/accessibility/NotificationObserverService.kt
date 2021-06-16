package top.littlefogcat.clickerx.core.accessibility

import android.app.Notification
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

/**
 * 监听系统通知的Service
 */
class NotificationObserverService : NotificationListenerService() {

    companion object {
        const val TAG = "NotificationService"
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val n = sbn.notification
        val e = n.extras
        val pkg = sbn.packageName
        val title = e.getString(Notification.EXTRA_TITLE)
        val content = e.getString(Notification.EXTRA_TEXT)
        if (pkg == packageName) {
            Log.d(TAG, "onNotificationPosted: $pkg/$title/$content")
            val service = ClickerXA11yService.instance
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                service?.unlock("249999")
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
    }
}