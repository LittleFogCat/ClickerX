package top.littlefogcat.clickerx.common.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class AlertDialogCompat {

    class Builder(private val lifecycleOwner: LifecycleOwner, context: Context) : AlertDialog.Builder(context), LifecycleEventObserver {
        private var dialog: AlertDialog? = null

        override fun create(): AlertDialog {
            val dialog = super.create()
            this.dialog = dialog
            lifecycleOwner.lifecycle.addObserver(this)
            return dialog
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                val d = dialog
                if (d != null && d.isShowing) {
                    d.dismiss()
                }
                dialog = null
            }
        }

    }

}