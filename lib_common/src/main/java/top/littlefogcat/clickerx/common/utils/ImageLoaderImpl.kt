package top.littlefogcat.clickerx.common.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import top.littlefogcat.clickerx.common.R

/**
 *
 * @Author：littlefogcat
 * @Date：2021/2/23-17:00
 * @Email：littlefogcat@foxmail.com
 */
object ImageLoaderImpl : ImageLoader {

    override fun loadImage(iv: ImageView, url: String) {
        Glide.with(iv)
            .load(url)
            .placeholder(R.drawable.img_load_error)
            .into(iv)
//        iv.setImageResource(R.drawable.ic_tab_message_round)
    }

    override fun loadImage(iv: ImageView, res: Int) {
        iv.setImageResource(res)
    }
}