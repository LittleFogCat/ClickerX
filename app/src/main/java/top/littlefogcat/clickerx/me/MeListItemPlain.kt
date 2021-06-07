package top.littlefogcat.clickerx.me

/**
 * [imgSrc] is the url of the image,
 * [text] is the text to show,
 * [target] is the target Activity's action,
 * [type] is the type is [MeListItem.TYPE_PLAIN]
 *
 * @Author：littlefogcat
 * @Date：2021/2/27-12:31
 * @Email：littlefogcat@foxmail.com
 */
class MeListItemPlain(
    val imgSrc: String,
    val text: String,
    override val target: String,
    override val type: Int = TYPE_PLAIN,
) : MeListItem(type, target)