package top.littlefogcat.clickerx.home.me

/**
 * @Author：littlefogcat
 * @Date：2021/2/28-17:20
 * @Email：littlefogcat@foxmail.com
 */
class MeListItemHeader(
    val imgSrc: String,
    val name: String,
    val desc: String,
    override val target: String?,
    override val type: Int = TYPE_HEADER
) : MeListItem(type, target)