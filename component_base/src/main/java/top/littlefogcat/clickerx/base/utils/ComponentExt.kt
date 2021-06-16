package top.littlefogcat.clickerx.base.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.facade.Postcard
import top.littlefogcat.clickerx.base.R
import top.littlefogcat.clickerx.base.base.BaseFragment
import top.littlefogcat.clickerx.base.base.BaseViewModel
import top.littlefogcat.common.utils.AlertDialogCompat

/**
 * 组件扩展函数。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-0:23
 * @Email littlefogcat@foxmail.com
 */

/**
 * 同[Activity.startActivity]
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
    cancelable: Boolean = true,
    positiveButtonText: String = "确定",
    positiveButtonClickListener: (() -> Unit)? = null,
) = AlertDialogCompat.Builder(this)
    .setTitle(title)
    .setMessage(content)
    .setPositiveButton(positiveButtonText) { _, _ -> positiveButtonClickListener?.invoke() }
    .setCancelable(cancelable)
    .create()

/**
 * 同:
 *
 * supportFragmentManager.beginTransaction()
 * .replace(id, frag)
 * .commit()
 */
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

/**
 * 通过[ViewModelProvider]获取[clazz]对应的ViewModel
 */
fun <T : BaseViewModel> BaseFragment<*>.getViewModel(clazz: Class<T>): T {
    return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[clazz]
}

/**
 * 通过[ViewModelProvider]获取[clazz]对应的ViewModel，这个ViewModel的生命周期跟Fragment所在的Activity相同。
 */
fun <T : BaseViewModel> BaseFragment<*>.getViewModelWithActivityLifecycle(clazz: Class<T>): T {
    return ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[clazz]
}

/**
 * 通过[ViewModelProvider]获取[clazz]对应的ViewModel
 */
fun <T : BaseViewModel> AppCompatActivity.getViewModel(clazz: Class<T>): T {
    return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[clazz]
}

fun Fragment.navigateBack() = findNavController().popBackStack()

/**
 * Set default transition for ARouter navigation.
 */
fun Postcard.asDefault(): Postcard {
    return withTransition(R.anim.rtl_enter_anim, R.anim.rtl_exit_anim)
}

/**
 * 检查是否得到监听通知权限
 */
private fun Context.isListenNotificationPermissionGranted(): Boolean {
    val pkgName = packageName
    val flat = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
    if (!flat.isNullOrEmpty()) {
        val names = flat.split(":")
        for (name in names) {
            val component = ComponentName.unflattenFromString(name)
            if (component != null && component.packageName == pkgName) {
                // 开启权限了，跳转到MainActivity
                return true
            }
        }
    }
    return false
}