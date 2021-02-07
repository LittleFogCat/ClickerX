package top.littlefogcat.compiler.scanning

import top.littlefogcat.compiler.analyze.Token
import top.littlefogcat.compiler.common.*

/**
 * 词法分析DFA
 *
 * 当前状态[state]由当前已扫描字符串[scanned]确定；
 * 当有输入[input]进来时，会根据当前状态[state]来决定单词扫描的结束与状态的转换。
 *
 * @Author：littlefogcat
 * @Date：2021/1/11-22:33
 * @Email：littlefogcat@foxmail.com
 */
class ScannerDFA /* (private val onTokenScanned: (Token) -> Unit) */ : DFA<Char, Int> {
    override var state: Int = STATE_INIT

    override val initialState: Int = STATE_INIT
    override val states: Set<Int> = setOf(STATE_INIT)
    override val alphabet: Set<Char> = setOf()
    override val final: Set<Int> = setOf()

    private var scanned = StringBuilder() // 当前状态下已经扫描过的字符序列
    private val statements: MutableList<MutableList<Token>> = mutableListOf()  // 语句List
    fun statements(): MutableList<MutableList<Token>> = statements.toMutableList()
    private var tokens: MutableList<Token> = mutableListOf() // 当前语句中的单词

    override fun input(x: Char) {
        if (x == '\n') { // 本行语句结束
            createToken(STATE_INIT)
            statements.add(tokens.toMutableList())
            tokens.clear()
            return
        }
        when (state) {
            STATE_INIT -> { // 初始无状态
                when {
                    x == ' ' -> return
                    x == '*' || x == '/' -> createToken(STATE_SINGLE)
                    isDelimiter(x) -> createToken(STATE_DELIMITER)
                    else -> state = when {
                        x.isLetter() -> STATE_IDN
                        x.isDigit() -> STATE_INTEGER
                        else -> when (x) {
                            '=' -> STATE_ASSIGN
                            '+' -> STATE_ADD
                            '-' -> STATE_SUB
                            '\"' -> STATE_STRING
                            '>' -> STATE_GT
                            '<' -> STATE_LT
                            '!' -> STATE_EXC
                            else -> throw DFAException(state, x)
                        }
                    }
                }

            }
            STATE_IDN -> continueOrMakeToken(x) { x.isLetterOrDigit() }
            STATE_INTEGER -> {
                if (x == '.') {
                    state = STATE_FLOAT
                } else {
                    continueOrMakeToken(x) { x.isDigit() }
                }
            }
            STATE_FLOAT -> continueOrMakeToken(x) { x.isDigit() }
            STATE_STRING -> {
                when (x) {
                    '\\' -> state = STATE_STRING_ESC
                    '"' -> createToken(STATE_STRING_END)
                    else -> scanned.append(x)
                }
            }
            STATE_STRING_ESC -> {
                scanned.append(x) // todo 转义？这里单纯的将转义符当做字符处理。
                state = STATE_STRING
            }
            STATE_ADD -> changeStateOrMakeToken(x, STATE_SELF_ADD) { x == '+' } // 加号+加号=自加
            STATE_SUB -> changeStateOrMakeToken(x, STATE_SELF_SUB) { x == '-' } // 减号+减号=自减
            STATE_ASSIGN -> changeStateOrMakeToken(x, STATE_EQ) { x == '=' } // 等号+等号=相等
            STATE_GT -> changeStateOrMakeToken(x, STATE_GOE) { x == '=' } // 大于号+等号=大于等于
            STATE_LT -> changeStateOrMakeToken(x, STATE_LOE) { x == '=' } // 小于号+等号=小于等于
            STATE_EXC -> changeStateOrMakeToken(x, STATE_NE) { x == '=' } // 感叹号+等号=不等于
        }
    }

    /**
     * 检查当前字符[x]是否满足条件[cond]。
     * 如果满足条件，那么就将字符添加到当前状态的临时字符串[scanned]中。
     * 否则，结束当前单词的扫描，开始扫描新的单词。
     */
    private fun continueOrMakeToken(x: Char, cond: (Char) -> Boolean) {
        changeStateOrMakeToken(x, state, cond)
    }

    /**
     * 对于当前输出[x]，如果其满足条件[cond]，那么将状态转换为[destState]；
     * 否则，结束当前单词的扫描，开始扫描新的单词。
     */
    private fun changeStateOrMakeToken(x: Char, destState: Int, cond: (Char) -> Boolean) {
        if (cond(x)) {
            state = destState
            scanned.append(x)
        } else {
            createToken()
            input(x)
        }
    }

    /**
     * 当一个单词扫描完毕，调用此函数，创建对应的[Token]。当前单词存储在临时字符串[scanned]中。
     * 该函数被调用，将根据[scanned]中的内容，生成[Token]对象，并调用[onTokenScanned]回调。
     *
     * 该函数执行完毕之后，状态[state]转换为[STATE_INIT]。
     *
     * Token分为：
     * [TOKEN_TYPE_UNDEFINE] <未指定>
     * [TOKEN_TYPE_KEYWORD] <关键字>
     * [TOKEN_TYPE_IDENTIFIER] <标识符>
     * [TOKEN_TYPE_CONST] <常量>
     * [TOKEN_TYPE_OPERATOR] <运算符>
     * [TOKEN_TYPE_DELIMITER] <界限符>
     */
    private fun createToken(st: Int = STATE_UNDEFINE) {
        if (st != STATE_UNDEFINE) {
            state = st
        }
        val tokenString = scanned.toString()
        val token: Token = when (state) {
            STATE_IDN -> {
                if (KEYWORDS.contains(tokenString)) { // 关键词，直接作为种别码
                    Token(tokenString)
                } else { // 标识符
                    Token(TOKEN_TYPE_IDENTIFIER, tokenString)
                }
            }
            STATE_INTEGER -> { // 整型，作为常量
                val num = tokenString.toIntOrNull()
                    ?: throw TokenCreateException(tokenString, "\"$tokenString\" is not a valid number format.")
                Token(TOKEN_TYPE_CONST, num)
            }
            STATE_FLOAT -> { // 浮点数，作为常量
                val num = tokenString.toFloatOrNull()
                    ?: throw TokenCreateException(tokenString, "\"$tokenString\" is not a valid number format.")
                Token(TOKEN_TYPE_CONST, num)
            }
            STATE_STRING_END -> { // 字符串，作为常量
                Token(TOKEN_TYPE_CONST, tokenString.substring(1)) // 第一个字符是引号
            }
            STATE_ADD, STATE_SELF_ADD, STATE_SUB, STATE_SELF_SUB,
            STATE_ASSIGN, STATE_EQ, STATE_GT, STATE_GOE, STATE_LT,
            STATE_LOE, STATE_EXC, STATE_NE, STATE_SINGLE -> { // 操作符类，直接作为种别码
                Token(tokenString)
            }
            else -> throw TokenCreateException(tokenString, "Unexpected state [$state].")
        }
        tokens.add(token)
        scanned.clear()
        state = STATE_INIT
    }

    companion object {
        const val STATE_UNDEFINE = -1 // 未定义
        const val STATE_INIT = 0 // 初始化的状态，没有任何输入
        const val STATE_IDN = 1 // 输入为字母数字串，可能为标识符或关键字
        const val STATE_INTEGER = 2 // 当前输入为一串数字，表示整数
        const val STATE_FLOAT = 10002 // 输入为数字与小数点，表示浮点数
        const val STATE_STRING = 3 // 输入为引号与字符，表示字符串
        const val STATE_STRING_ESC = 10003 // 字符中的转义符号"\"，等待输入下一个字符与"\"结合为转义符
        const val STATE_STRING_END = 20003 // 字符串的第二个引号已经输入，表示字符串已经完结
        const val STATE_ADD = 4 // 当前输入为一个加号
        const val STATE_SELF_ADD = 10004 // 当前输入为两个加号
        const val STATE_SUB = 5 // 当前输入为一个减号
        const val STATE_SELF_SUB = 10005 // 当前输入为两个减号
        const val STATE_ASSIGN = 6 // 当前输入为一个等号
        const val STATE_EQ = 10006 // 当前输入为两个等号
        const val STATE_GT = 7 // 当前输入为一个大于号（greater than）
        const val STATE_GOE = 10007 // 当前输入为大于等于（>=）
        const val STATE_LT = 8 // 当前输入为一个小于号（less than）
        const val STATE_LOE = 10008 // 当前输入为小于等于（<=）
        const val STATE_EXC = 9 // 当前输入为一个感叹号（exclamation）
        const val STATE_NE = 10009 // 当前输入为不等于（!=）
        const val STATE_SINGLE = 10 // 当前输入为单字操作符（*/）
        const val STATE_DELIMITER = 10 // 当前输入为界限符（():）
        val FINAL_STATES = setOf( // 可以为终态的状态
            STATE_IDN, STATE_INTEGER, STATE_FLOAT, STATE_STRING_END, STATE_ADD,
            STATE_SELF_ADD, STATE_SUB, STATE_SELF_SUB, STATE_ASSIGN, STATE_EQ,
            STATE_GT, STATE_GOE, STATE_LT, STATE_LOE, STATE_EXC, STATE_NE
        )
    }
}
