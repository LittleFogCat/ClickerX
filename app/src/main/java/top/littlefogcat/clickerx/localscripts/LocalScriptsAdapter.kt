package top.littlefogcat.clickerx.localscripts

import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.LocalScriptsItemBinding
import top.littlefogcat.clickerx.core.Script

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
