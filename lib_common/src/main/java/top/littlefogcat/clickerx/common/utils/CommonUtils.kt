package top.littlefogcat.clickerx.common.utils

import android.widget.Toast

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */

fun showToast(msg: String) {
    Toast.makeText(AppContext.get(), msg, Toast.LENGTH_SHORT).show()
}

fun Any?.equalsOrNull(o: Any?): Boolean {
    return this != null && (o == null || equals(o))
}