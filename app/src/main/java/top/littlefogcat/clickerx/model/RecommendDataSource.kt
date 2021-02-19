package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.RecommendItem

/**
 * 推荐相关接口
 */
interface RecommendDataSource {
    /**
     * 获取推荐列表
     */
    fun getRecommendList(): List<RecommendItem>

    fun getRecommendSearchList(): List<RecommendSearchItem>
}