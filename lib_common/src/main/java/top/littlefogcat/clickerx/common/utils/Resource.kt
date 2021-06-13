package top.littlefogcat.clickerx.common.utils

import android.app.Application
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.ContextCompat

/**
 * @Author：littlefogcat
 * @Date：2021/2/23-15:00
 * @Email：littlefogcat@foxmail.com
 */
object Resource {
    private lateinit var app: Application
    fun register(app: Application) {
        this.app = app
    }

    fun getDrawable(id: Int): Drawable? {
        return ContextCompat.getDrawable(app, id)
    }

    fun getString(id: Int): String {
        return app.getString(id)
    }

    fun idToUri(id: Int): Uri {
        return Uri.parse("android.resource://${app.packageName}/$id")
    }
}