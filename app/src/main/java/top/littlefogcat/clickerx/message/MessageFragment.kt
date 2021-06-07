package top.littlefogcat.clickerx.message

import android.os.Bundle
import android.view.View
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseViewPagerFragment
import top.littlefogcat.clickerx.databinding.MessageFragBinding

class MessageFragment : BaseViewPagerFragment<MessageFragBinding>() {
    override val layoutId: Int = R.layout.message_frag

    override fun onDataBinding(binding: MessageFragBinding) {
        binding.viewModel = MessageViewModel()
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