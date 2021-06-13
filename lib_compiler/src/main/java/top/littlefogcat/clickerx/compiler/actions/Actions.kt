package top.littlefogcat.clickerx.compiler.actions

/**
 * @Author：littlefogcat
 * @Date：2020/12/24-22:46
 * @Email：littlefogcat@foxmail.com
 */
val EmptyAction = XAction("", emptyList())
const val ACTION_TYPE_COMMAND = "command"
const val ACTION_TYPE_DEF = "def"
const val ACTION_TYPE_ASSIGN = "assign"
const val ACTION_TYPE_IF = "if"
const val ACTION_TYPE_FOR = "for"