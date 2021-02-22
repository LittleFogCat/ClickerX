package top.littlefogcat.clickerx.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
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
        .commit()
}

fun AppCompatActivity.replaceFragmentIfAbsent(id: Int, frag: Fragment) {
    supportFragmentManager.findFragmentById(id)
        ?: replaceFragment(id, frag)
}

fun Context.loadDrawable(resId: Int) =
    ResourcesCompat.getDrawable(resources, resId, theme)

fun AppCompatActivity.loadColor(colorId: Int) =
    ResourcesCompat.getColor(resources, colorId, theme)

fun Activity.todo() {
    Toast.makeText(this, "功能建设中……", Toast.LENGTH_SHORT).show()
}

fun Fragment.todo() {
    Toast.makeText(context, "功能建设中……\"", Toast.LENGTH_SHORT).show()
}

fun View.todo() {
    Toast.makeText(context, "功能建设中……\"", Toast.LENGTH_SHORT).show()
}
