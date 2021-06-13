package top.littlefogcat.clickerx.me

import android.os.Bundle
import android.util.Log
import android.view.View
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.common.base.BaseFragment
import top.littlefogcat.clickerx.databinding.MeFragBinding
import top.littlefogcat.clickerx.common.utils.getViewModel

/**
 * Fragment for **ME** page.
 *
 * @Author：littlefogcat
 * @Date：2021/2/26-13:21
 * @Email：littlefogcat@foxmail.com
 */
class MeFragment() : BaseFragment<MeFragBinding>() {
    override val layoutId: Int = R.layout.me_frag

    override fun onDataBinding(binding: MeFragBinding) {
        binding.viewModel = getViewModel(MeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: view=$view, bindingRoot=${binding.root}")
        binding.run {
            listView.adapter = MeAdapter(context ?: return)
            viewModel?.loadData()
        }
    }

    private fun initList() {

    }

    companion object {
        fun newInstance() = MeFragment()
    }
}