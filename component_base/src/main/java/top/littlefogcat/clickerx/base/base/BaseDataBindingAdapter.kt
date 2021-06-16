package top.littlefogcat.clickerx.base.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

//import androidx.recyclerview.widget.RecyclerView

/**
 * 加入了DataBinding的RecyclerView.Adapter基类，在RecyclerView中实现了DataBinding，适合简单列表。
 * 只支持绑定一种数据类型，重写[layoutId]设置布局，重写[onBindViewHolder]抽象函数实现数据绑定。
 *
 * 其中[T]是数据类型，在xml中使用；[B]是对应布局的数据绑定类。
 *
 * 实现类只需要重写布局id[layoutId]和[onBindViewHolder]一个抽象函数即可。
 */
abstract class BaseDataBindingAdapter<T, B : ViewDataBinding> : RecyclerView.Adapter<BaseDataBindingAdapter.ViewHolder>() {
    protected val TAG = javaClass.simpleName // TAG

    /**
     * 数据列表，只支持一种类型的数据
     */
    protected val data = mutableListOf<T>()
    override fun getItemCount(): Int = data.size

    /**
     * 布局文件的id，在子类中实现
     */
    abstract val layoutId: Int

    /**
     * ViewHolder，因为实现数据绑定，所以实际操作由[binding]实现。
     */
    open class ViewHolder(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context), layoutId, parent, false
        )
        return ViewHolder(binding)
    }

    /**
     * 更新数据
     */
    fun setData(_data: List<T>?) {
        Log.v(TAG, "setData: $_data")
        data.clear()
        if (!_data.isNullOrEmpty()) data.addAll(_data)
        notifyDataSetChanged()
    }

    final override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        @Suppress("UNCHECKED_CAST")
        onBindViewHolder(holder.binding as B, data[position])
        holder.binding.executePendingBindings()
    }

    /**
     * 显示数据时使用。通过[binding]设置布局中对应的变量[item]更新数据。
     */
    abstract fun onBindViewHolder(binding: B, item: T)

}