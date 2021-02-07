package top.littlefogcat.compiler.compiling

import top.littlefogcat.compiler.analyze.Token
import top.littlefogcat.compiler.scanning.Scanner
import java.io.InputStream

/**
 * 编译过程：
 * 源码 -> Token -> 分析树
 *
 * @Author：littlefogcat
 * @Date：2020/12/24-22:24
 * @Email：littlefogcat@foxmail.com
 */
class Compiler {
    private val identifiers = mutableSetOf<String>() // 标识符表
    private val scanner = Scanner().apply { }

    fun compile(code: String) {
        val scannedTokens = scanner.scan(code)
        handleTokens(scannedTokens)
    }

    fun compile(input: InputStream) {
        val scannedTokens = scanner.scan(input)
        handleTokens(scannedTokens)
    }

    fun handleTokens(tokensList: List<List<Token>>) {
    }


//    fun compile(s: String): List<XAction> = compile(s.split("\n").toList())
//
//    fun compile(lines: List<String>): List<XAction> {
//        val xActions = arrayListOf<XAction>()
//        val actionStack = arrayListOf<List<XAction>>() // 栈，用于子语句
//        actionStack.add(xActions)
//
//        for (line in lines) {
//            val action = compileLine(line)
//            when (action.type) {
//                ACTION_TYPE_COMMAND -> {
//
//                }
//                ACTION_TYPE_DEF -> {
//                }
//                ACTION_TYPE_ASSIGN -> {
//                }
//                ACTION_TYPE_IF -> {
//                }
//                ACTION_TYPE_FOR -> {
//                }
//
//            }
//        }
//    }
//
//    /**
//     * 五种语句类型：指令、定义变量、赋值、if、for
//     */
//    private fun compileLine(orig: String?): XAction {
//        if (orig.isNullOrBlank()) return EmptyAction
//        var line = orig.trim()
//        while (line.endsWith(";")) {
//            line = line.removeSuffix(";")
//        }
//        val action: XAction
//        val varargs = arrayListOf<String>()
//        val subActions = arrayListOf<XAction>()
//        if (line.startsWith("if:")) { // if
//            action = IfAction()
//        } else if (line.startsWith("for:")) { // for
//            action = XAction(ACTION_TYPE_FOR, varargs, subActions)
//        } else if (line.startsWith("def:")) { // 定义
//            action = XAction(ACTION_TYPE_DEF, varargs, subActions)
//        } else if (line.contains(":")) { // 指令
//            action = XAction(ACTION_TYPE_COMMAND, varargs, subActions)
//        } else if (line.contains("=")) { // 赋值
//            action = XAction(ACTION_TYPE_ASSIGN, varargs, subActions)
//        } else { // 无法解析
//            throw CompileException("无法解析语句：$orig")
//        }
//        return action
//    }
}