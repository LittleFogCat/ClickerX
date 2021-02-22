package top.littlefogcat.clickerx.home

import android.util.Log
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.HomeRecyItemBinding
import top.littlefogcat.clickerx.model.entities.RecommendItem

/**
 * 推荐列表的Adapter
 */
class RecommendListAdapter : BaseDataBindingAdapter<RecommendItem, HomeRecyItemBinding>() {

    override fun onBindViewHolder(binding: HomeRecyItemBinding, item: RecommendItem) {
        Log.v(TAG, "onBindViewHolder: ${item.title}")
        binding.item = item
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.home_recy_item
}