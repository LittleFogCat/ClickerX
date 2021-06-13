package top.littlefogcat.clickerx.compiler.common

/**
 * 词法分析中用到的一些常量
 *
 * @Author：littlefogcat
 * @Date：2020/12/26-18:17
 * @Email：littlefogcat@foxmail.com
 */

// ---------- 单词类型 ------------
const val TOKEN_TYPE_UNDEFINE = "UND" // 未指定
const val TOKEN_TYPE_KEYWORD = "KWD" // 关键字
const val TOKEN_TYPE_IDENTIFIER = "IDN" // 标识符
const val TOKEN_TYPE_CONST = "CST" // 常量
const val TOKEN_TYPE_OPERATOR = "OPR" // 运算符
const val TOKEN_TYPE_DELIMITER = "DLT" // 界限符
val TOKEN_TYPES = setOf(
    TOKEN_TYPE_UNDEFINE,
    TOKEN_TYPE_KEYWORD,
    TOKEN_TYPE_IDENTIFIER,
    TOKEN_TYPE_CONST,
    TOKEN_TYPE_OPERATOR,
    TOKEN_TYPE_DELIMITER
)


// --------- 关键词 ----------
const val KEYWORD_IF = "if"
const val KEYWORD_FOR = "for"
const val KEYWORD_VAR = "var"
const val KEYWORD_VAL = "val"
const val KEYWORD_ENDIF = "endif"
const val KEYWORD_ENDFOR = "endfor"
const val KEYWORD_ELSE = "else"
const val KEYWORD_OR = "or"
const val KEYWORD_AND = "and"
const val KEYWORD_CLASS = "class"
const val KEYWORD_INTERFACE = "interface"
const val KEYWORD_OBJECT = "object"
const val KEYWORD_ABSTRACT = "abstract"
const val KEYWORD_FUN = "fun"
const val KEYWORD_WHEN = "when"
const val KEYWORD_NULL = "null"
const val KEYWORD_TRUE = "true"
const val KEYWORD_FALSE = "false"
const val KEYWORD_SWITCH = "switch"
const val KEYWORD_BREAK = "break"
const val KEYWORD_RETURN = "return"
const val KEYWORD_IS = "is"
const val KEYWORD_THIS = "this"
const val KEYWORD_PUBLIC = "public"
const val KEYWORD_PRIVATE = "private"
val KEYWORDS = setOf(
    KEYWORD_IF,
    KEYWORD_FOR,
    KEYWORD_VAR,
    KEYWORD_VAL,
    KEYWORD_ENDIF,
    KEYWORD_ENDFOR,
    KEYWORD_ELSE,
    KEYWORD_OR,
    KEYWORD_AND,
    KEYWORD_CLASS,
    KEYWORD_INTERFACE,
    KEYWORD_OBJECT,
    KEYWORD_ABSTRACT,
    KEYWORD_FUN,
    KEYWORD_WHEN,
    KEYWORD_NULL,
    KEYWORD_TRUE,
    KEYWORD_FALSE,
    KEYWORD_SWITCH,
    KEYWORD_BREAK,
    KEYWORD_RETURN,
    KEYWORD_IS,
    KEYWORD_THIS,
    KEYWORD_PUBLIC,
    KEYWORD_PRIVATE
)

// --------- 运算符 ----------
const val OPERATOR_ASSIGN = "=" // 赋值
const val OPERATOR_ADD = "+" // 加
const val OPERATOR_SUB = "-" // 减
const val OPERATOR_MUL = "*" // 乘
const val OPERATOR_DEV = "/" // 除
const val OPERATOR_EQ = "==" // 等于
const val OPERATOR_NE = "!=" // 不等于
const val OPERATOR_BIG = ">" // 大于
const val OPERATOR_SML = "<" // 小于
const val OPERATOR_BOE = ">=" // 大于等于
const val OPERATOR_SOE = "<=" // 小于等于
const val OPERATOR_OR = "&&" // 或
const val OPERATOR_AND = "||" // 与
val OPERATORS = setOf(
    OPERATOR_ASSIGN,
    OPERATOR_ADD,
    OPERATOR_SUB,
    OPERATOR_MUL,
    OPERATOR_DEV,
    OPERATOR_EQ,
    OPERATOR_NE,
    OPERATOR_BIG,
    OPERATOR_SML,
    OPERATOR_BOE,
    OPERATOR_SOE,
    OPERATOR_OR,
    OPERATOR_AND
)
val BI_OP = setOf( // 二元运算符
    OPERATOR_ADD,
    OPERATOR_SUB,
    OPERATOR_MUL,
    OPERATOR_DEV,
    OPERATOR_EQ,
    OPERATOR_NE,
    OPERATOR_BIG,
    OPERATOR_SML,
    OPERATOR_BOE,
    OPERATOR_SOE,
    OPERATOR_OR,
    OPERATOR_AND
)

// ---------- 界限符 ----------
const val DELIMITER_LP = "(" // 左括号 left parentheses
const val DELIMITER_RP = ")" // 右括号 right parentheses
const val DELIMITER_CL = ":" // 冒号 colon
val DELIMITERS = setOf(
    DELIMITER_LP,
    DELIMITER_RP,
    DELIMITER_CL
)

/**
 * 字符[c]是否是界限符。
 * 包括 '(', ')', ':'
 */
fun isDelimiter(c: Char): Boolean = DELIMITERS.contains(c.toString())





