package top.littlefogcat.clickerx.home.model.repo

import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.RecommendItem
import top.littlefogcat.clickerx.base.entities.RecommendSearchItem
import top.littlefogcat.clickerx.home.model.datasource.RecommendDataSource
import top.littlefogcat.clickerx.home.model.services.RecommendService
import top.littlefogcat.clinj.InjectSrc
import top.littlefogcat.network.NetResult
import top.littlefogcat.network.RetrofitCreator

@InjectSrc(FlavorConstants.NAME_RECOMMEND_DS, FlavorConstants.FLAVOR_PROD)
object RecommendRepositoryProd : RecommendDataSource {
    private val remote by lazy { RetrofitCreator.create(RecommendService::class.java) }

    override suspend fun getRecommendList(): NetResult<List<RecommendItem>> {
        return remote.getRecommendList()
    }

    override suspend fun getRecommendSearchList(): NetResult<List<RecommendSearchItem>> {
        return remote.getRecommendSearchList()
    }
}