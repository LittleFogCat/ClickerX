package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.db.entities.Chat

interface MessageDataSource {
    fun loadChatList(): List<Chat>
}