package top.littlefogcat.sample

import android.widget.TextView
import java.lang.StringBuilder
import java.lang.ref.WeakReference

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object OperationRecorder {
    private val string = StringBuilder()
    private lateinit var receiver: WeakReference<TextView>

    fun attach(view: TextView) {
        receiver = WeakReference(view)
    }

    fun onClick(who: CharSequence) {
        receiver.get()?.append("click: $who\n")
//        receiver.get()?.text = string
    }
}