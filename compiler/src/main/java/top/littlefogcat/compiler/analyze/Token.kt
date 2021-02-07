package top.littlefogcat.compiler.analyze

/**
 * 一个单词。
 *
 * Token：词法单元
 *
 * 内容：<种别码，属性值>
 *
 * @Author：littlefogcat
 * @Date：2020/12/26-18:13
 * @Email：littlefogcat@foxmail.com
 */
class Token(val type: String, val attr: Any? = null)