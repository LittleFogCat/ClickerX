package top.littlefogcat.clickerx.utils

import android.widget.ImageView
import top.littlefogcat.clickerx.R

/**
 *
 * @Author：littlefogcat
 * @Date：2021/2/23-17:00
 * @Email：littlefogcat@foxmail.com
 */
object ImageLoaderImpl : ImageLoader {

    override fun loadImage(iv: ImageView, @Suppress("UNUSED_PARAMETER") url: String) {
        iv.setImageResource(R.drawable.ic_tab_message_round)
    }

    override fun loadImage(iv: ImageView, res: Int) {
        iv.setImageResource(res)
    }
}