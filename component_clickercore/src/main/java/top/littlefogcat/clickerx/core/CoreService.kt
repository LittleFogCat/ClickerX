package top.littlefogcat.clickerx.core

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import top.littlefogcat.clickerx.base.constants.RouteConstants

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Route(path = RouteConstants.ROUTE_SERVICE_CORE)
class CoreService : IProvider {
    override fun init(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        // post the event to initialize Core
        Core.init(metrics.widthPixels, metrics.heightPixels)
    }
}