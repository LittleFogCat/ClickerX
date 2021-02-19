package top.littlefogcat.clickerx.message

import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.databinding.MessageFragBinding
import top.littlefogcat.clickerx.R

class MessageFragment private constructor() : BaseFragment<MessageFragBinding>() {
    override val layoutId: Int = R.layout.message_frag

    override fun onCreateViewModel() {
        binding.viewModel = MessageViewModel()
    }

    companion object {
        fun newInstance() = MessageFragment()
    }
}