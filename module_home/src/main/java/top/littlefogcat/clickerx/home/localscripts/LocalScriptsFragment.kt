package top.littlefogcat.clickerx.home.localscripts

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.base.base.BaseViewPagerFragment
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clickerx.base.constants.INTENT_KEY_SCRIPT
import top.littlefogcat.clickerx.base.constants.RouteConstants
import top.littlefogcat.clickerx.base.utils.getViewModel
import top.littlefogcat.clickerx.home.R
import top.littlefogcat.clickerx.home.databinding.LocalScriptsFragBinding

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
                    .build(RouteConstants.ROUTE_ACTIVITY_SCRIPT_DETAIL)
                    .withObject(INTENT_KEY_SCRIPT, script)
                    .withTransition(R.anim.rtl_enter_anim, R.anim.rtl_exit_anim)
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