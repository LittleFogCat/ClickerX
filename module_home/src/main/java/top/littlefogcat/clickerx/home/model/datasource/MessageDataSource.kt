package top.littlefogcat.clickerx.home.model.datasource

import top.littlefogcat.clickerx.base.entities.Chat
import top.littlefogcat.network.NetResult

interface MessageDataSource {
    suspend fun loadChatList(): NetResult<List<Chat>>
}