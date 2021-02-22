package top.littlefogcat.clickerx.message

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.databinding.MessageFragBinding
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseViewPagerFragment

class MessageFragment private constructor() : BaseViewPagerFragment<MessageFragBinding>() {
    override val layoutId: Int = R.layout.message_frag

    override fun onCreateViewModel() {
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