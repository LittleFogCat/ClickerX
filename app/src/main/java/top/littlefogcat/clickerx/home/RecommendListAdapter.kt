package top.littlefogcat.clickerx.home

import android.util.Log
import android.widget.Toast
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.HomeRecyItemBinding
import top.littlefogcat.clickerx.model.entities.RecommendItem

/**
 * 推荐列表的Adapter
 */
class RecommendListAdapter : BaseDataBindingAdapter<RecommendItem, HomeRecyItemBinding>() {
    override val layoutId = R.layout.home_recy_item

    override fun onBindViewHolder(binding: HomeRecyItemBinding, item: RecommendItem) {
        Log.v(TAG, "onBindViewHolder: ${item.title}")
        binding.item = item
        binding.root.setOnClickListener {
            Toast.makeText(it.context, "别点了，这是假的", Toast.LENGTH_SHORT).show()
        }
    }

}