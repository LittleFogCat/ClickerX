package top.littlefogcat.clickerx.common.base

import android.widget.BaseAdapter

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseDataBindingListAdapter<T> : BaseAdapter() {
    protected val data = arrayListOf<T>()

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): T {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(d: List<T>) {
        data.clear()
        data.addAll(d)
        notifyDataSetChanged()
    }
}