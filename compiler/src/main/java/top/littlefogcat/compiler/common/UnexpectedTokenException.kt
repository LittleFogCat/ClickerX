package top.littlefogcat.compiler.common

import java.lang.Exception

/**
 * 遇到了意料之外的Token。
 * 一般是语法有误。
 *
 * @Author：littlefogcat
 * @Date：2021/1/12-2:05
 * @Email：littlefogcat@foxmail.com
 */
class UnexpectedTokenException(val index: Int) : Exception("Unexpected tokens")