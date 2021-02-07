package top.littlefogcat.base.utils

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @Author：littlefogcat
 * @Date：2020/12/23-23:17
 * @Email：littlefogcat@foxmail.com
 */
fun fileRead(path: String) = File(path).readText()
fun fileReadLines(path: String) = File(path).readLines()
fun fileWrite(path: String, content: String) = File(path).writeText(content)
fun fileAppend(path: String, content: String) = File(path).appendText(content)

fun Context.readAssets(path: String): List<String> = assets.open(path).readStringList()
fun Context.readRaw(id: Int): List<String> = resources.openRawResource(id).readStringList()

private fun InputStream.readStringList(): List<String> {
    val lines = mutableListOf<String>()
    val br = BufferedReader(InputStreamReader(this))
    var line: String
    while (br.readLine().also { line = it } != null) {
        lines.add(line)
    }
    return lines
}
