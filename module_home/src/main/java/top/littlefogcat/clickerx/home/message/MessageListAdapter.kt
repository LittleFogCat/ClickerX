package top.littlefogcat.clickerx.home.message

import top.littlefogcat.clickerx.base.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.base.utils.todo
import top.littlefogcat.clickerx.home.R
import top.littlefogcat.clickerx.home.databinding.MessageChatItemBinding

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