package top.littlefogcat.compiler.analyze

import top.littlefogcat.compiler.common.*

/**
 * 词法分析器。
 * 逐行扫描源代码，识别出其中的单词，并将其转化成[Token]的形式。
 *
 * @Author：littlefogcat
 * @Date：2020/12/26-18:19
 * @Email：littlefogcat@foxmail.com
 */
object Scanner {

    /**
     * 将一句代码转换为单词列表。
     */
    fun scan(stmt: String): List<Token> {
        val tokens = arrayListOf<Token>()
        var start = 0
        /**
         * 从start作为起点，找出[stmt]的下一个单词。
         *
         * 一个单词结束的标志
         * - 空格；
         * - 如果第一个字符是字母或数字（说明这是一个关键词或标识符或数字），遇到非字母或数字结束；
         * - 如果第一个字符是符号（说明这是一个操作符），遇到非符号结束；
         * - 如果是界限符（括号和冒号），则直接结束；
         * - 如果是其他字符，
         */
        while (start < stmt.length) {
            // 分割单词
            var next: Int
            if (' ' == stmt[start]) { // 开头是空格，跳过
                start++
                continue
            }
            if ('\"' == stmt[start]) {
                next = stmt.indexOf('\"', start + 1) { stmt[it - 1] != '\\' }
                if (next == -1) throw LexicalAnalyzeException("无法解析行$stmt，from $start", start)
                next++
            } else if (stmt[start].isDelimiter()) {
                next = start + 1
            } else {
                next = start
                while (++next < stmt.length) {
                    if (stmt[next] == ' ') break
                    if (stmt[start].isNumber() && stmt[next].isNumber() ||
                        stmt[start].isAlphabet() && stmt[next].isNumberOrAlphabet() ||
                        stmt[start].isOperator() && stmt[next].isOperator()
                    ) continue
                    if (stmt[start].isNumberOrAlphabet() && (stmt[next].isOperator() || stmt[next].isDelimiter()) ||
                        stmt[start].isOperator() && stmt[next].isNumberOrAlphabet()
                    ) break
                    throw LexicalAnalyzeException("无法解析行：$stmt", start)
                }
            }
            val nextToken = try {
                wordToToken(stmt.substring(start, next))
            } catch (e: LexicalAnalyzeException) {
                throw LexicalAnalyzeException("无法解析行：$stmt", start)
            }
            tokens.add(nextToken)
            start = next
        }
        return tokens
    }

    /**
     * 检查一个单词是否合法，并将其转换为对应的[Token]。
     * 如果这个单词不合法，那么将抛出[LexicalAnalyzeException]。
     */
    private fun wordToToken(word: String): Token {
        if (KEYWORDS.contains(word) or OPERATORS.contains(word) or DELIMITERS.contains(word)) {
            // 是关键字或者操作符或者分隔符，直接使用单词作为tokenType
            return Token(word)
        } else { // 只能是标识符或者常数
            val isNumber = word[0] in '0'..'9' // 如果以数字开头，则必然是数字
            val isString = word[0] == '\"' && word.last() == '\"' && word.length > 1 // 字符串
            for ((i, c) in word.withIndex()) {
                if ((i == 0 || i == word.lastIndex) && isString) continue
                if (word[0].isNumber() && !c.isNumber() ||
                    !c.isNumberOrAlphabet()
                ) throw LexicalAnalyzeException("无法解析：$word", 0)
            }
            if (isNumber) return Token(TOKEN_TYPE_CONST, word.toDouble()) // 数字常量
            if (isString) return Token(TOKEN_TYPE_CONST, word.substring(1, word.lastIndex - 1)) // 字符串常量
            return Token(TOKEN_TYPE_IDENTIFIER, word) // 标识符
        }
    }

    private fun Char.isNumber() = this in '0'..'9'
    private fun Char.isAlphabet() = this in 'a'..'z' || this in 'A'..'Z'
    private fun Char.isNumberOrAlphabet() = isNumber() || isAlphabet()
    private fun Char.isOperator() = OPERATORS.contains(this.toString())
    private fun Char.isDelimiter() = DELIMITERS.contains(this.toString())
    private fun isValidChar(c: Char) = c.isNumberOrAlphabet() || c.isOperator() || c.isDelimiter()
}