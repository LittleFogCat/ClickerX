package top.littlefogcat.clickerx.home.model.repo

import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.Chat
import top.littlefogcat.clickerx.home.model.datasource.MessageDataSource
import top.littlefogcat.clickerx.home.model.services.MessageService
import top.littlefogcat.clinj.InjectSrc
import top.littlefogcat.network.BaseRepository
import top.littlefogcat.network.NetResult
import top.littlefogcat.network.RetrofitCreator

@InjectSrc(FlavorConstants.NAME_MESSAGE_DS, FlavorConstants.FLAVOR_PROD)
object MessageRepositoryProd : BaseRepository(), MessageDataSource {
    private val service by lazy { RetrofitCreator.create(MessageService::class.java) }

    override suspend fun loadChatList(): NetResult<List<Chat>> {
        return service.loadChatList()
    }
}
