package top.littlefogcat.common.utils

import android.widget.ImageView

/**
 * @Author：littlefogcat
 * @Date：2021/2/27-16:37
 * @Email：littlefogcat@foxmail.com
 */
interface ImageLoader {
    companion object {
        fun get(): ImageLoader = ImageLoaderImpl
    }

    fun loadImage(iv: ImageView, url: String)
    fun loadImage(iv: ImageView, res: Int)
}