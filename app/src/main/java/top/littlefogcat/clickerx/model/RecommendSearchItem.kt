package top.littlefogcat.clickerx.model

data class RecommendSearchItem(
    val id: Long,
    val title: String,
    val subtitle: String,
    val heat: Int = 0
)