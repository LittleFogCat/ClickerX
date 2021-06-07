package top.littlefogcat.clickerx.message

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.base.BaseViewModel
import top.littlefogcat.clickerx.model.entities.Chat

class MessageViewModel : BaseViewModel() {
    private val messageDataSource = Injector.provideMessageDataSource()

    val chatList = MutableLiveData<List<Chat>>()
    val messageChatItemList = MutableLiveData<List<MessageChatItem>>()

    fun loadChatList() {
        runOnIO {
            val cl = messageDataSource.loadChatList()
            val mcl = cl.map {
                val msgs = it.messages
                val lastMsg = msgs.last()
                MessageChatItem(it.partnerId, it.avatar, it.title, lastMsg.content, lastMsg.time)
            }.sortedBy {
                -it.lastMessageTime
            }
            messageChatItemList.postValue(mcl)
        }

    }
}