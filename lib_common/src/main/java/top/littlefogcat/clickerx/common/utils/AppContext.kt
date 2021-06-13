package top.littlefogcat.clickerx.common.utils

import android.app.Application

/**
 * Application context for this app. To get application anywhere, use [AppContext.get].
 *
 * Should invoke [AppContext.init] function as soon as possible to set the context.
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class AppContext {
    companion object {
        private lateinit var INSTANCE: Application
        fun get() = INSTANCE
        fun init(application: Application) {
            INSTANCE = application
        }
    }
}