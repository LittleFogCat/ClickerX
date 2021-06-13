package top.littlefogcat.clickerx.db.entities

/**
 * 一个对话，包含了若干消息。
 * Entity class of a chat.
 *
 * [id] 消息id。the id of this chat
 * [type] the type of this chat, may be personal, group, system, etc.
 * [partnerId] who this chat belongs to. When [type] is personal, this chat belongs to the other side of this chat;
 * when [type] is group, this chat belongs to the user who create the group; and system chat belongs to the system user.
 * [title] the title of this chat.
 * [avatar] the avatar url of this chat.
 * [messages] the messages of this chat, sorted by send date.
 */
data class Chat(
    val id: Long,
    val type: Int,
    val partnerId: Long,
    val title: String,
    val avatar: String,
    val messages: List<Message>
) {
    companion object {
        const val TYPE_SYSTEM = 0
        const val TYPE_PUSH = 1
        const val TYPE_PERSONAL = 2
        const val TYPE_GROUP = 3
    }
}