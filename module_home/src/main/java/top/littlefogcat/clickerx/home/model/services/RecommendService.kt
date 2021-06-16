package top.littlefogcat.clickerx.home.model.services

import retrofit2.http.GET
import top.littlefogcat.clickerx.base.entities.RecommendItem
import top.littlefogcat.clickerx.base.entities.RecommendSearchItem
import top.littlefogcat.clickerx.home.model.datasource.RecommendDataSource
import top.littlefogcat.network.NetResult

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface RecommendService : RecommendDataSource {
    @GET("/recommend/main")
    override suspend fun getRecommendList(): NetResult<List<RecommendItem>>

    @GET("/recommend/search")
    override suspend fun getRecommendSearchList(): NetResult<List<RecommendSearchItem>>
}