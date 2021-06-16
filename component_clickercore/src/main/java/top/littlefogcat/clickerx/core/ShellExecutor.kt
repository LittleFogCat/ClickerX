package top.littlefogcat.clickerx.core

import top.littlefogcat.clickerx.base.constants.DEFAULT_SERVER_SOCKET_PORT
import top.littlefogcat.clickerx.base.constants.SOCKET_PORT_FILE_PATH
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.net.Socket
import java.util.concurrent.Executors

/**
 * 通过Socket连接运行在系统中的进程，执行高权限的ADB指令。
 *
 * 需要配合使用：https://www.jianshu.com/p/1452ec79c400
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class ShellExecutor {
    private lateinit var mClient: Socket
    private val thread = Executors.newSingleThreadExecutor()

    private fun getClint(): Socket {
        if (this::mClient.isInitialized) return mClient
        mClient = try {
            val portFile = File(SOCKET_PORT_FILE_PATH)
            val content = portFile.readText().trim()
            val port = content.toInt()
            Socket("127.0.0.1", port)
        } catch (e: Exception) {
            Socket("127.0.0.1", DEFAULT_SERVER_SOCKET_PORT)
        }
        return mClient
    }

    fun execute(cmd: String, callback: ((String) -> Unit)? = null, onError: ((Exception) -> Unit)? = null) {
        thread.execute {
            try {
                val client = getClint()
                val output = DataOutputStream(client.getOutputStream())
                output.writeUTF(cmd)
                val input = DataInputStream(client.getInputStream())
                val result = input.readUTF()
                callback?.invoke(result)
            } catch (e: Exception) {
                onError?.invoke(e)
                e.printStackTrace()
            }
        }
    }
}