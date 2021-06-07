package top.littlefogcat.clickerx.me

/**
 * [type] - item类型。
 *
 * @Author：littlefogcat
 * @Date：2021/2/26-22:32
 * @Email：littlefogcat@foxmail.com
 */
abstract class MeListItem(
    open val type: Int,
    open val target: String? = null
) {
    companion object {
        const val TYPE_PLAIN = 0
        const val TYPE_HEADER = 1
        const val TYPE_DECORATOR = 2
        val DIVIDER = object : MeListItem(TYPE_DECORATOR, null) {}
    }
}