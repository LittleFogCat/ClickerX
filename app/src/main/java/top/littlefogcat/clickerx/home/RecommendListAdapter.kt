package top.littlefogcat.clickerx.home

import android.util.Log
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.AbstractDataBindingAdapter
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.ItemRecommendBinding
import top.littlefogcat.clickerx.model.entities.RecommendItem

class RecommendListAdapter : BaseDataBindingAdapter<RecommendItem, RecommendListAdapter.ViewHolder>() {

    class ViewHolder(override val binding: ItemRecommendBinding) : AbstractDataBindingAdapter.ViewHolder(binding) {

    }

    override fun <B> onCreateViewHolder(binding: B): ViewHolder = ViewHolder(binding as ItemRecommendBinding)

    override fun onBindViewHolder(holder: ViewHolder, item: RecommendItem) {
        Log.v(TAG, "onBindViewHolder: ${item.title}")
        holder.binding.item = item
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.item_recommend
}