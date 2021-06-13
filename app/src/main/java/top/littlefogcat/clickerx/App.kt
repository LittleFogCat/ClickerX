package top.littlefogcat.clickerx

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.db.ClickerXDatabase
import top.littlefogcat.clickerx.common.utils.AppContext
import top.littlefogcat.clickerx.common.utils.Resource

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.init(this)
        ClickerXDatabase.init(this)
        if (BuildConfig.DEBUG) {
            // this should be invoked before ARouter.init()
            ARouter.openDebug()
        }
        ARouter.init(this)
        Resource.register(this)
    }
}