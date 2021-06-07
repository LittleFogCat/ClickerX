package top.littlefogcat.clickerx.utils

import android.widget.Toast
import top.littlefogcat.clickerx.App

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */

fun showToast(msg: String) {
    Toast.makeText(App.get(), msg, Toast.LENGTH_SHORT).show()
}

fun Any?.equalsOrNull(o: Any?): Boolean {
    return this != null && (o == null || equals(o))
}