package top.littlefogcat.compiler.parsing

import top.littlefogcat.compiler.analyze.Token

/**
 *
 * 语法分析器。
 * 识别出句子中的短语，并构建语法分析树[ParseTree]。
 *
 * 输入：Token列表
 * 输入类型：List<List<Token>>
 *
 * 输出：todo
 * 输出类型：todo
 *
 * 文法：
 * // P: 开始符号
 * // SList: 语句列表
 * P -> SList
 *
 * // S: 语句
 * SList -> S | SList \n S
 *
 * // E: Expression 表达式
 * // P: params 参数列表
 * // id: 标识符
 * S -> id = E // 1. 赋值语句
 * S -> if E \n SList \n endif //  2. 判断语句
 * S -> for E \n SList \n endfor // 3. 循环语句
 * S -> id P // 4. 函数调用
 *
 * // const: 常量
 * // bi_op: 二元运算符
 * // un_op: 一元运算符
 * E -> (E)
 * E -> E bi_op E
 * E -> un_op E
 * E -> id
 * E -> const
 *
 * P -> E | P E
 *
 * @Author：littlefogcat
 * @Date：2021/1/24-6:24
 * @Email：littlefogcat@foxmail.com
 */
class Parser {

    fun parse(tokensList: List<List<Token>>) {

    }

    private fun parseLine(tokens: List<Token>): ParseTree {
        val pt = ParseTree(ParseTree.TYPE_STATEMENT)
        pt.generate(tokens)
        return pt
    }

    private fun ParseTree.generate(tokens: List<Token>) {
        when (type) {
            ParseTree.TYPE_STATEMENT -> {
            }
            ParseTree.TYPE_EXPRESSION -> {
            }
            ParseTree.TYPE_PARAM -> {
            }
        }
    }
}