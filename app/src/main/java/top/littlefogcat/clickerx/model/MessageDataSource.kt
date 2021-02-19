package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.Chat

interface MessageDataSource {
    fun loadChatList(): List<Chat>
}