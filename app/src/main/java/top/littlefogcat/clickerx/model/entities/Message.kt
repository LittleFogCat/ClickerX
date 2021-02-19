package top.littlefogcat.clickerx.model.entities

data class Message(
    val id: Long,
    val type: Int,
    val title: String,
    val desc: String,
    val content: String,
    val read: Boolean,
    val time: Long
)