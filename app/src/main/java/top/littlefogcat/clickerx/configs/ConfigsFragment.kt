package top.littlefogcat.clickerx.configs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.base.BaseViewPagerFragment
import top.littlefogcat.clickerx.databinding.ConfigsFragBinding

/**
 * 配置列表。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-2:08
 * @Email littlefogcat@foxmail.com
 */
class ConfigsFragment private constructor() : BaseViewPagerFragment<ConfigsFragBinding>() {
    override val layoutId = R.layout.configs_frag

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
//            adapter = ConfigsAdapter() todo
        }
    }

    companion object {
        fun newInstance() = ConfigsFragment()
    }

    override fun onCreateViewModel() {
        binding.viewModel = ConfigsViewModel()
    }

}