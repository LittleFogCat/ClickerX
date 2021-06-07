package top.littlefogcat.clickerx.message

import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.databinding.MessageChatItemBinding
import top.littlefogcat.clickerx.utils.todo

/**
 * @Author：littlefogcat
 * @Date：2021/2/22-21:19
 * @Email：littlefogcat@foxmail.com
 */
class MessageListAdapter(override val layoutId: Int = R.layout.message_chat_item) :
    BaseDataBindingAdapter<MessageChatItem, MessageChatItemBinding>() {

    override fun onBindViewHolder(binding: MessageChatItemBinding, item: MessageChatItem) {
        binding.item = item
        binding.root.setOnClickListener {
            it.todo()
        }
    }

}