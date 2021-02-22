package top.littlefogcat.clickerx.configs

import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.AbstractDataBindingAdapter
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.ConfigsFragBinding
import top.littlefogcat.clickerx.model.entities.Config

/**
 * @Author littlefogcat
 * @Date 2020/8/4-1:19
 * @Email littlefogcat@foxmail.com
 */
class ConfigsAdapter : BaseDataBindingAdapter<Config, ConfigsFragBinding>() {
    override fun onBindViewHolder(binding: ConfigsFragBinding, item: Config) {

    }

    override fun getLayoutId(viewType: Int): Int = R.layout.configs_frag

}