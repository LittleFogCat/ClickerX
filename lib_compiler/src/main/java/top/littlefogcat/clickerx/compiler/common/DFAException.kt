package top.littlefogcat.clickerx.compiler.common

import java.lang.Exception

/**
 * DFA出错。
 * 一般是拼写错误或非法字符。
 *
 * @Author：littlefogcat
 * @Date：2021/1/12-1:38
 * @Email：littlefogcat@foxmail.com
 */
class DFAException(state: Int, input: Any) :
    Exception("Input [$input] is not available on state [$state]")