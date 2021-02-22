package top.littlefogcat.clickerx.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 针对DataBinding制作的RecyclerView.Adapter的抽象类。
 *
 * @see BaseDataBindingAdapter
 */
abstract class AbstractDataBindingAdapter<T>
    : RecyclerView.Adapter<AbstractDataBindingAdapter.ViewHolder>() {
    protected val TAG = javaClass.simpleName

    protected val data = mutableListOf<T>()

    open class ViewHolder(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    abstract override fun getItemViewType(position: Int): Int
    abstract fun getLayoutId(viewType: Int): Int

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(createDataBinding(parent, viewType))
//        onCreateViewHolder<ViewDataBinding>(createDataBinding(parent, viewType), viewType)

//    abstract fun <T : ViewDataBinding> onCreateViewHolder(binding: T, viewType: Int): ViewHolder

    internal open fun <T : ViewDataBinding> createDataBinding(parent: ViewGroup, viewType: Int): T =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), getLayoutId(viewType), parent, false
        )

    fun setData(_data: List<T>?) {
        Log.v(TAG, "setData: $_data")
        data.clear()
        if (!_data.isNullOrEmpty()) data.addAll(_data)
        notifyDataSetChanged()
    }
}