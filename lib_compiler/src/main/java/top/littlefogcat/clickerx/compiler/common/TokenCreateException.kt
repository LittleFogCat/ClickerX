package top.littlefogcat.clickerx.compiler.common

/**
 * @Author：littlefogcat
 * @Date：2021/1/12-19:16
 * @Email：littlefogcat@foxmail.com
 */
class TokenCreateException(tokenString: String, msg: String? = null) :
    Exception("Unable to create token: $tokenString." + if (msg == null) "" else " With message[ $msg ]")