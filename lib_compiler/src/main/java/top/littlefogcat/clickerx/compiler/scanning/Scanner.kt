package top.littlefogcat.clickerx.compiler.scanning

import top.littlefogcat.clickerx.compiler.analyze.Token
import java.io.InputStream

/**
 * Scanner 词法分析
 *
 * 输入：源码
 * 输入类型：String或InputStream
 *
 * 输出：Token列表
 * 输出类型：List<List<Token>>
 *
 * @Author：littlefogcat
 * @Date：2021/1/11-22:52
 * @Email：littlefogcat@foxmail.com
 */
class Scanner {

    fun scan(inputStream: InputStream): List<List<Token>> = ScannerDFA().apply {
        inputStream.use { input ->
            var read: Int
            while (input.read().also { read = it } != -1) {
                input(read.toChar())
            }
        }
    }.statements()

    fun scan(code: String): List<List<Token>> = ScannerDFA().apply {
        for (ch in code) {
            input(ch)
        }
    }.statements()

//    fun scanLine(line: String): List<Token> {
//        val tokenList = mutableListOf<Token>()
//        if (!line.startsWith("//")) {
//            val dfa = ScannerDFA { tokenList.add(it) }
//            for (ch in line) {
//                dfa.input(ch)
//            }
//            dfa.input(' ')
//        }
//        return tokenList
//    }
}