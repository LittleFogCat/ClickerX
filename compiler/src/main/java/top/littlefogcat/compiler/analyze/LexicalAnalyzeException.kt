package top.littlefogcat.compiler.analyze

import java.lang.Exception

/**
 * 词法分析错误。
 * [msg] 错误信息
 * [index] 出错位置
 *
 * @Author：littlefogcat
 * @Date：2020/12/26-20:30
 * @Email：littlefogcat@foxmail.com
 */
class LexicalAnalyzeException(private val msg: String, private val index: Int) : Exception(msg)