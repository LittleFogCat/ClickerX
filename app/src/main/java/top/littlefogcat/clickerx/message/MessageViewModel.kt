package top.littlefogcat.clickerx.message

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.base.BaseViewModel
import top.littlefogcat.clickerx.model.entities.Chat

class MessageViewModel : BaseViewModel() {
    val messageDataSource = Injector.provideMessageDataSource()
    val chatList = MutableLiveData<List<Chat>>()

    fun loadChatList() {
        runOnIO(chatList) {
            messageDataSource.loadChatList()
        }
    }
}