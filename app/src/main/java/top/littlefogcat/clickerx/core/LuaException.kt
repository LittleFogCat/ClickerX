package top.littlefogcat.clickerx.core

import java.lang.RuntimeException

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class LuaException : RuntimeException {
    private val errorCode: Int
    private val src: String

    constructor(
        errorCode: Int, src: String
    ) : super("Exception while running lua: code = $errorCode(${codeToString(errorCode, src)}), src = $src") {
        this.errorCode = errorCode
        this.src = src
    }

    constructor(
        errorCode: Int,
        src: String,
        message: String?
    ) : super("Exception while running lua: code = $errorCode(${codeToString(errorCode, src)}), src = $src, message = $message") {
        this.errorCode = errorCode
        this.src = src
    }



    companion object {
        const val NULL_SERVICE = 0
        const val NULL_SCRIPT = 1
        const val API_VERSION_TOO_LOW = 2
        const val PACKAGE_NOT_FOUND = 3

        fun codeToString(errorCode: Int, src: String): String {
            return when (errorCode) {
                NULL_SERVICE -> "Core.service is null, expected not."
                NULL_SCRIPT -> "Core.runningScript is null, expected not."
                API_VERSION_TOO_LOW -> "Api version for [$src] is too low."
                PACKAGE_NOT_FOUND -> "Package not found for [$src]."
                else -> "Unknown error code."
            }
        }
    }

    override fun toString(): String {
        return "LuaException: ${codeToString(errorCode, src)}"
    }

}