package top.littlefogcat.clickerx.home.message

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.base.base.BaseViewModel
import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.Chat
import top.littlefogcat.clickerx.home.model.datasource.MessageDataSource
import top.littlefogcat.clinj.Inject

class MessageViewModel : BaseViewModel() {
    @top.littlefogcat.clinj.Inject(FlavorConstants.NAME_MESSAGE_DS)
    lateinit var messageDataSource: MessageDataSource

    val chatList = MutableLiveData<List<Chat>>()
    val messageChatItemList = MutableLiveData<List<MessageChatItem>>()

    fun loadChatList() {
        runOnIO {
            messageDataSource.loadChatList().resolveIfSuccess { chatList ->
                val sortedChatList = chatList.map {
                    val msgs = it.messages
                    val lastMsg = msgs.last()
                    MessageChatItem(it.partnerId, it.avatar, it.title, lastMsg.content, lastMsg.time)
                }.sortedBy {
                    -it.lastMessageTime
                }
                messageChatItemList.postValue(sortedChatList)
            }
        }

    }
}