package top.littlefogcat.clickerx.compiler.common

/**
 * 定义DFA的接口。
 *
 * DFA的五要素：
 * 有穷状态集[states]、输入字母表[alphabet]、转换函数[input]、初始状态[initialState]、终止状态集[final]。
 *
 * 泛型中的[IN]表示输入的类型，[S]表示状态类型。
 *
 * @Author：littlefogcat
 * @Date：2021/1/11-22:08
 * @Email：littlefogcat@foxmail.com
 */
interface DFA<IN, S> {
    var state: S // 当前自动机的状态

    val states: Set<S> // 有穷状态集
    val final: Set<S> // 终止状态集
    val alphabet: Set<IN> // 输入字母表
    val initialState: S // 初始状态
    fun input(x: IN) // 转换函数
}