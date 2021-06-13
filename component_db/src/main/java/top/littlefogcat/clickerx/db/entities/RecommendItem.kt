package top.littlefogcat.clickerx.db.entities

class RecommendItem(
    val title: String,
    val author: User,
    val summary: String,
    val votes: Int,
    val comments: Int
)