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
class ConfigsAdapter : BaseDataBindingAdapter<Config, ConfigsAdapter.ViewHolder>() {

    class ViewHolder(binding: ConfigsFragBinding) : AbstractDataBindingAdapter.ViewHolder(binding) {}

    override fun <B> onCreateViewHolder(binding: B): ViewHolder {
        return ViewHolder(binding as ConfigsFragBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Config) {
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.configs_frag
    }
}