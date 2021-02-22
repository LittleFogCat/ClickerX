package top.littlefogcat.clickerx.message

import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.MessageChatItemBinding

/**
 * @Author：littlefogcat
 * @Date：2021/2/22-21:19
 * @Email：littlefogcat@foxmail.com
 */
class MessageListAdapter : BaseDataBindingAdapter<MessageChatItem, MessageChatItemBinding>() {
    override fun onBindViewHolder(binding: MessageChatItemBinding, item: MessageChatItem) {
        binding.item = item
        binding.root.setOnClickListener {

        }
    }

    override fun getLayoutId(viewType: Int) = R.layout.message_chat_item
}