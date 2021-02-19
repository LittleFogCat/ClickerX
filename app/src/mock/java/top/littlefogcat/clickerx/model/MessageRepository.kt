package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.Chat

class MessageRepository : MessageDataSource {
    override fun loadChatList(): List<Chat> {
        return arrayListOf()
    }
}