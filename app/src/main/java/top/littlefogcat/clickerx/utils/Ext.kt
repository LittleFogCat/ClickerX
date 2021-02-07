package top.littlefogcat.clickerx.utils

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @Author littlefogcat
 * @Date 2020/8/4-0:23
 * @Email littlefogcat@foxmail.com
 */
fun AppCompatActivity.launchActivity(clazz: Class<out AppCompatActivity>) {
    startActivity(Intent(this, clazz))
}

/**
 * 辅助创建dialog
 */
fun AppCompatActivity.makeDialog(
    title: String,
    content: String = "",
    positiveButtonText: String = "确定",
    positiveButtonClickListener: DialogInterface.OnClickListener? = null,
    cancelable: Boolean = true
) = AlertDialog.Builder(this)
    .setTitle(title)
    .setMessage(content)
    .setPositiveButton(positiveButtonText, positiveButtonClickListener)
    .setCancelable(cancelable)
    .create()

fun AppCompatActivity.replaceFragment(id: Int, frag: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(id, frag)
}

fun AppCompatActivity.replaceFragmentIfAbsent(id: Int, frag: Fragment) {
    supportFragmentManager.findFragmentById(id)
        ?: replaceFragment(id, frag)
}