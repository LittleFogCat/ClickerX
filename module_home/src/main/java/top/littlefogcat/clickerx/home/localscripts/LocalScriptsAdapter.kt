package top.littlefogcat.clickerx.home.localscripts

import top.littlefogcat.clickerx.base.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clickerx.home.R
import top.littlefogcat.clickerx.home.databinding.LocalScriptsItemBinding

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class LocalScriptsAdapter(val onItemClick: OnItemClick) : BaseDataBindingAdapter<Script, LocalScriptsItemBinding>() {
    override val layoutId = R.layout.local_scripts_item
    override fun onBindViewHolder(binding: LocalScriptsItemBinding, item: Script) {
        binding.item = item
        binding.itemClick = onItemClick
    }

    interface OnItemClick {
        fun onClick(script: Script)
    }
}
