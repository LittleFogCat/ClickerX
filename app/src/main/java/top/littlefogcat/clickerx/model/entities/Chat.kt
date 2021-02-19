package top.littlefogcat.clickerx.model.entities

/**
 * Entity class of a chat.
 *
 * [id] the id of this chat
 * [type] the type of this chat, may be personal, group, system, etc.
 * [belongTo] who this chat belongs to. When [type] is personal, this chat belongs to the other side of this chat;
 * when [type] is group, this chat belongs to the user who create the group; and system chat belongs to the system user.
 * [name] the name of this chat.
 * [avatar] the avatar url of this chat.
 * [messages] the messages of this chat, sorted by send date.
 */
data class Chat(
    val id: Int,
    val type: Int,
    val belongTo: User,
    val name: String,
    val avatar: String,
    val messages: List<Message>
)