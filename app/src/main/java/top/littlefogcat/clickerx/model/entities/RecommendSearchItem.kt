package top.littlefogcat.clickerx.model.entities

data class RecommendSearchItem(
    val id: Long,
    val title: String,
    val subtitle: String,
    val heat: Int = 0
)