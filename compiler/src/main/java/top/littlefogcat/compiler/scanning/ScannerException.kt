package top.littlefogcat.compiler.scanning

import java.lang.Exception

/**
 * 词法分析异常。
 * 当词法分析出现错误时抛出异常。
 * 原文[statement]，异常信息[msg]，出现错误的坐标[index]。
 *
 * @Author：littlefogcat
 * @Date：2021/1/12-1:33
 * @Email：littlefogcat@foxmail.com
 */
class ScannerException(val statement: String, val msg: String, val index: Int) : Exception(msg)