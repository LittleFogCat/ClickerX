package top.littlefogcat.clickerx.db.entities

/**
 * 一条消息。
 * 包括消息的id[id]，发送者id[sender]，发送时间[time]，消息内容[content]，是否已读[read]
 */
data class Message(
    val id: Long,
    val sender: Long,
    val time: Long,
    val content: String,
    val read: Boolean,
)