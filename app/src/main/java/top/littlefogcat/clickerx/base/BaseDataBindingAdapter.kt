package top.littlefogcat.clickerx.base

/**
 * 加入了DataBinding的RecyclerView.Adapter基类。
 *
 * 其中[T]是数据类型，在xml中使用；[VH]是view holder的类型。
 * 只需要重写[onCreateViewHolder]和[onBindViewHolder]两个抽象函数即可。
 */
abstract class BaseDataBindingAdapter<T, B> : AbstractDataBindingAdapter<T>() {

    override fun getItemViewType(position: Int): Int = 0
    override fun getItemCount(): Int = data.size

//    abstract fun <B> onCreateViewHolder(binding: B): ViewHolder

    final override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        @Suppress("UNCHECKED_CAST")
        onBindViewHolder(holder.binding as B, data[position])
        holder.binding.executePendingBindings()
    }

    abstract fun onBindViewHolder(binding: B, item: T)

}