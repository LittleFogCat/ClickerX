package top.littlefogcat.clickerx.home.message

import android.os.Bundle
import android.view.View
import top.littlefogcat.clickerx.base.base.BaseViewPagerFragment
import top.littlefogcat.clickerx.base.utils.getViewModel
import top.littlefogcat.clickerx.home.R
import top.littlefogcat.clickerx.home.databinding.MessageFragBinding

class MessageFragment : BaseViewPagerFragment<MessageFragBinding>() {
    override val layoutId: Int = R.layout.message_frag

    override fun onDataBinding(binding: MessageFragBinding) {
        binding.viewModel = getViewModel(MessageViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            recyclerView.adapter = MessageListAdapter()
            viewModel?.loadChatList()
        }
    }

    companion object {
        fun newInstance() = MessageFragment()
    }
}