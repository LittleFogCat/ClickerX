package top.littlefogcat.clickerx.localscripts

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseViewPagerFragment
import top.littlefogcat.clickerx.core.Script
import top.littlefogcat.clickerx.databinding.LocalScriptsFragBinding
import top.littlefogcat.clickerx.utils.INTENT_KEY_CONFIG
import top.littlefogcat.clickerx.utils.ROUTE_ACTIVITY_CONFIG_DETAIL
import top.littlefogcat.clickerx.utils.getViewModel

/**
 * @Author：littlefogcat
 * @Date：2021/2/23-2:53
 * @Email：littlefogcat@foxmail.com
 */
class LocalScriptsFragment : BaseViewPagerFragment<LocalScriptsFragBinding>() {
    override val layoutId = R.layout.local_scripts_frag

    override fun onDataBinding(binding: LocalScriptsFragBinding) {
        binding.viewModel = getViewModel(LocalScriptsViewModel::class.java)
        binding.recyclerView.adapter = LocalScriptsAdapter(object : LocalScriptsAdapter.OnItemClick {
            override fun onClick(script: Script) {
                ARouter.getInstance()
                    .build(ROUTE_ACTIVITY_CONFIG_DETAIL)
                    .withObject(INTENT_KEY_CONFIG, script)
                    .navigation(activity)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        binding.apply {
            viewModel?.loadLocalConfigs()
        }
    }

    companion object {
        fun newInstance() = LocalScriptsFragment()
    }
}