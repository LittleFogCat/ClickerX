package top.littlefogcat.clickerx.base

import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingAdapter<T, VH : AbstractDataBindingAdapter.ViewHolder> : AbstractDataBindingAdapter<T>() {

    override fun getItemViewType(position: Int): Int = 0
    override fun getItemCount(): Int = data.size

    final override fun <B : ViewDataBinding> onCreateViewHolder(binding: B, viewType: Int) =
        onCreateViewHolder(binding)

    abstract fun <B> onCreateViewHolder(binding: B): VH

    final override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        @Suppress("UNCHECKED_CAST")
        onBindViewHolder(holder as VH, data[position])
        holder.binding.executePendingBindings()
    }

    abstract fun onBindViewHolder(holder: VH, item: T)

}