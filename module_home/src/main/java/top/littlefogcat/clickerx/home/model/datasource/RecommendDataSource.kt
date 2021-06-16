package top.littlefogcat.clickerx.home.model.datasource

import top.littlefogcat.clickerx.base.entities.RecommendItem
import top.littlefogcat.clickerx.base.entities.RecommendSearchItem
import top.littlefogcat.network.NetResult

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface RecommendDataSource {
    suspend fun getRecommendList(): NetResult<List<RecommendItem>>
    suspend fun getRecommendSearchList(): NetResult<List<RecommendSearchItem>>
}