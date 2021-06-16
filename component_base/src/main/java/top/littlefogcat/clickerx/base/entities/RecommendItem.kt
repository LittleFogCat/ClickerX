package top.littlefogcat.clickerx.base.entities

/**
 * The recommended item.
 *
 * @param id the ID of the associated script.
 * @param title the title.
 * @param author the author.
 * @param desc the description.
 * @param votes number of likes.
 * @param comments number of comments.
 */
data class RecommendItem(
    val id: Long,
    val title: String,
    val author: UserSimple,
    val desc: String,
    val votes: Int,
    val comments: Int
)