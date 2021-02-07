package top.littlefogcat.accessibility

/**
 * 模拟操作-指令的实体类，对应json的字符串。
 *
 * [command] - 指令名。
 * [content] - 指令内容。
 *
 *
 * @Author：littlefogcat
 * @Date：2020/12/24-21:55
 * @Email：littlefogcat@foxmail.com
 */
data class SimuAction(
    val command: String,
    val content: String
)
