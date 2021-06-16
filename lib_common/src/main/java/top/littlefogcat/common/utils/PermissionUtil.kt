package top.littlefogcat.common.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings


@Suppress("unused")
object PermissionUtil {
    /**
     * Build.MANUFACTURER判断各大手机厂商品牌
     */
    private const val MANUFACTURER_HUAWEI = "Huawei" //华为
    private const val MANUFACTURER_MEIZU = "Meizu" //魅族
    private const val MANUFACTURER_XIAOMI = "Xiaomi" //小米
    private const val MANUFACTURER_SONY = "Sony" //索尼
    private const val MANUFACTURER_OPPO = "OPPO"
    private const val MANUFACTURER_LG = "LG"
    private const val MANUFACTURER_VIVO = "vivo"
    private const val MANUFACTURER_SAMSUNG = "samsung" //三星
    private const val MANUFACTURER_LETV = "Letv" //乐视
    private const val MANUFACTURER_ZTE = "ZTE" //中兴
    private const val MANUFACTURER_YULONG = "YuLong" //酷派
    private const val MANUFACTURER_LENOVO = "LENOVO" //联想
    var isAppSettingOpen = false
    const val PERMISSION_SETTING_FOR_RESULT = 0x100

    /**
     * 跳转到相应品牌手机系统权限设置页，如果跳转不成功，则跳转到应用详情页
     * 这里需要改造成返回true或者false，应用详情页:true，应用权限页:false
     *
     * @param activity
     */
    fun GoToSetting(activity: Activity) {
        when (Build.MANUFACTURER) {
            MANUFACTURER_HUAWEI -> Huawei(activity)
            MANUFACTURER_MEIZU -> Meizu(activity)
            MANUFACTURER_XIAOMI -> Xiaomi(activity)
            MANUFACTURER_SONY -> Sony(activity)
            MANUFACTURER_OPPO -> OPPO(activity)
            MANUFACTURER_LG -> LG(activity)
            MANUFACTURER_LETV -> Letv(activity)
            else -> try { //防止应用详情页也找不到，捕获异常后跳转到设置，这里跳转最好是两级，太多用户也会觉得麻烦，还不如不跳
                openAppDetailSetting(activity)
            } catch (e: java.lang.Exception) {
                SystemConfig(activity)
            }
        }
    }

    /**
     * 华为跳转权限设置页
     *
     * @param activity
     */
    fun Huawei(activity: Activity) {
        try {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("packageName", activity.packageName)
            val comp = ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity")
            intent.component = comp
            activity.startActivityForResult(intent, PERMISSION_SETTING_FOR_RESULT)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * 魅族跳转权限设置页，测试时，点击无反应，具体原因不明
     *
     * @param activity
     */
    fun Meizu(activity: Activity) {
        try {
            val intent = Intent("com.meizu.safe.security.SHOW_APPSEC")
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.putExtra("packageName", activity.packageName)
            activity.startActivity(intent)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * 小米，功能正常
     *
     * @param activity
     */
    fun Xiaomi(activity: Activity) {
        try { // MIUI 8 9
            val localIntent = Intent("miui.intent.action.APP_PERM_EDITOR")
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
            localIntent.putExtra("extra_pkgname", activity.getPackageName())
            activity.startActivityForResult(localIntent, PERMISSION_SETTING_FOR_RESULT)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            try { // MIUI 5/6/7
                val localIntent = Intent("miui.intent.action.APP_PERM_EDITOR")
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
                localIntent.putExtra("extra_pkgname", activity.getPackageName())
                activity.startActivityForResult(localIntent, PERMISSION_SETTING_FOR_RESULT)
                isAppSettingOpen = false
            } catch (e1: java.lang.Exception) { // 否则跳转到应用详情
                openAppDetailSetting(activity)
            }
        }
    }

    /**
     * 索尼，6.0以上的手机非常少，基本没看见
     */
    fun Sony(activity: Activity) {
        try {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("packageName", activity.packageName)
            val comp = ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity")
            intent.component = comp
            activity.startActivity(intent)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * OPPO
     * @param activity
     */
    fun OPPO(activity: Activity) {
        try {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("packageName", activity.packageName)
            val comp = ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity")
            intent.component = comp
            activity.startActivity(intent)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * LG经过测试，正常使用
     *
     * @param activity
     */
    fun LG(activity: Activity) {
        try {
            val intent = Intent("android.intent.action.MAIN")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("packageName", activity.packageName)
            val comp = ComponentName("com.android.settings", "com.android.settings.Settings\$AccessLockSummaryActivity")
            intent.component = comp
            activity.startActivity(intent)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * 乐视6.0以上很少，基本都可以忽略了，现在乐视手机不多
     * @param activity
     */
    fun Letv(activity: Activity) {
        try {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("packageName", activity.packageName)
            val comp = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps")
            intent.component = comp
            activity.startActivity(intent)
            isAppSettingOpen = false
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * 只能打开到自带安全软件
     * @param activity
     */
    fun _360(activity: Activity) {
        try {
            val intent = Intent("android.intent.action.MAIN")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("packageName", activity.packageName)
            val comp = ComponentName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity")
            intent.component = comp
            activity.startActivity(intent)
        } catch (e: java.lang.Exception) {
            openAppDetailSetting(activity)
        }
    }

    /**
     * 系统设置界面
     * @param activity
     */
    fun SystemConfig(activity: Activity) {
        val intent = Intent(Settings.ACTION_SETTINGS)
        activity.startActivity(intent)
    }

    /**
     * 获取应用详情页面
     * @return
     */
    private fun getAppDetailSettingIntent(activity: Activity): Intent {
        val localIntent = Intent()
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
        localIntent.data = Uri.fromParts("package", activity.getPackageName(), null)
        return localIntent
    }

    fun openAppDetailSetting(activity: Activity) {
        activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT)
        isAppSettingOpen = true
    }
}