package top.littlefogcat.clickerx.home.message

/**
 * 消息主页面RecyclerView的item，对应一条聊天。
 *
 * @Author：littlefogcat
 * @Date：2021/2/22-20:58
 * @Email：littlefogcat@foxmail.com
 */
class MessageChatItem(
    val partnerId: Long,
    val avatarUrl: String,
    val title: String,
    val content: String,
    var lastMessageTime: Long
)