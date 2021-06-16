package top.littlefogcat.clickerx.home.me

import android.os.Bundle
import android.util.Log
import android.view.View
import top.littlefogcat.clickerx.base.base.BaseFragment
import top.littlefogcat.clickerx.base.utils.getViewModel
import top.littlefogcat.clickerx.home.R
import top.littlefogcat.clickerx.home.databinding.MeFragBinding

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