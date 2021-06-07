package top.littlefogcat.sample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.View
import top.littlefogcat.component.GlobalFloatingWindow

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val gfw = GlobalFloatingWindow.show(this, R.layout.gfw)
        gfw.findViewById<View>(R.id.dismiss).setOnClickListener {
            gfw.dismiss()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
