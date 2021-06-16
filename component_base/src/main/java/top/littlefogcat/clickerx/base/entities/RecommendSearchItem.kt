package top.littlefogcat.clickerx.base.entities

/**
 * The recommended searching item.
 */
data class RecommendSearchItem(
    val id: Long,
    val title: String,
    val subtitle: String,
    val heat: Int = 0
)