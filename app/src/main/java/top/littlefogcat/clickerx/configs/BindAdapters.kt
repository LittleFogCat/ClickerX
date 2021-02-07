package top.littlefogcat.clickerx.configs

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import top.littlefogcat.clickerx.model.Config

/**
 * @Author littlefogcat
 * @Date 2020/8/4-1:17
 * @Email littlefogcat@foxmail.com
 */
class BindAdapters {
    @BindingAdapter("items")
    fun setItems(recyclerView: RecyclerView, items: List<Config>) {
        val adapter = recyclerView.adapter

    }
}