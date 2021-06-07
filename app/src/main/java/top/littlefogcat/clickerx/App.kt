package top.littlefogcat.clickerx

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.utils.Resource

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class App : Application() {
    companion object {
        fun get(): App = inst!!
        private var inst: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        inst = this
        ARouter.init(this)
        Resource.register(this)
        AppInitializer.init(this)
    }
}