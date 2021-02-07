package top.littlefogcat.compiler.parsing

/**
 * @Author：littlefogcat
 * @Date：2021/1/24-7:04
 * @Email：littlefogcat@foxmail.com
 */
class ParseTree(val type: Int) {

    companion object {
        const val TYPE_STATEMENT = 0
        const val TYPE_EXPRESSION = 1
        const val TYPE_PARAM = 2
        const val TYPE_ID = 3
        const val TYPE_CONST = 4
    }
}
