package top.littlefogcat.clickerx.compiler.actions

/**
 * 一条语句对应的行为。
 *
 * @Author：littlefogcat
 * @Date：2020/12/24-22:29
 * @Email：littlefogcat@foxmail.com
 */
open class XAction(
    val type: String, // command指令 / def定义变量 / assign赋值 / if判断 / for循环
    val varargs: List<String>, // 执行参数
    val subActions: List<XAction>? = null // 子行为，用于判断与循环中
)
