package top.littlefogcat.clickerx.compiler.analyze

/**
 * 返回字符串中从[start]位置开始，并且满足条件[ensure]的第一个字符[ch]的位置。
 *
 * @Author：littlefogcat
 * @Date：2020/12/26-22:19
 * @Email：littlefogcat@foxmail.com
 */
fun String.indexOf(ch: Char, start: Int, ensure: (Int) -> Boolean): Int {
    var s = start
    while (s < length) {
        val next = indexOf(ch, s)
        if (ensure(next)) return next
        s++
    }
    return -1
}