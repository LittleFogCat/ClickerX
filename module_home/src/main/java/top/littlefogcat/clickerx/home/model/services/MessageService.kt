package top.littlefogcat.clickerx.home.model.services

import retrofit2.http.POST
import top.littlefogcat.clickerx.base.entities.Chat
import top.littlefogcat.clickerx.home.model.datasource.MessageDataSource
import top.littlefogcat.network.NetResult

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface MessageService : MessageDataSource {
    @POST("/message/chatlist")
    override suspend fun loadChatList(): NetResult<List<Chat>>
}