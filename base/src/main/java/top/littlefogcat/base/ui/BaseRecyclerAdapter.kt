package top.littlefogcat.base.ui

import androidx.recyclerview.widget.RecyclerView

/**
 * 作者：littlefogcat
 * 创建日期：2020/8/16-22:52
 * Email：littlefogcat@foxmail.com
 */
abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {
    private val data = mutableListOf<T>()

    override fun getItemCount(): Int {
        var size = data.size
        if (hasHeader()) size++
        if (hasFooter()) size++
        return size
    }

    open fun hasHeader(): Boolean = false
    open fun hasFooter(): Boolean = false

}