package top.littlefogcat.clickerx.utils

import android.widget.ImageView
import top.littlefogcat.clickerx.Injector

/**
 * @Author：littlefogcat
 * @Date：2021/2/27-16:37
 * @Email：littlefogcat@foxmail.com
 */
interface ImageLoader {
    companion object {
        fun get(): ImageLoader = Injector.provideImageLoader()
    }

    fun loadImage(iv: ImageView, url: String)
    fun loadImage(iv: ImageView, res: Int)
}