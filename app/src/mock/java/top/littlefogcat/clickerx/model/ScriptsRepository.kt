package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.db.entities.Script
import top.littlefogcat.clickerx.db.entities.Script.Companion.STATE_DEFAULT
import top.littlefogcat.clickerx.common.utils.AppContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import kotlin.random.Random

/**
 * 配置文件数据源实现类（Mock）
 *
 * 作者：littlefogcat
 * 创建日期：2020/8/4-16:41
 * Email：littlefogcat@foxmail.com
 */
object ScriptsRepository : ScrpitsDataSource {
    const val TAG = "LocalConfigsRepository"

    private val fakeData = hashMapOf(
        Pair(0L, createAntForest()),
        Pair(2L, createOpenChrome()),
        Pair(3L, createSendMessageScript(3L)),
        Pair(4L, createRedPacketScript(4L))
    )

    private fun readAsset(filename: String): String {
        try {
            val app = AppContext.get()
            val assetManager = app.assets
            val stream = assetManager.open(filename)
            BufferedReader(InputStreamReader(stream)).useLines {
                val sb = StringBuilder()
                for (line in it) {
                    sb.append(line).appendLine()
                }
                return sb.toString()
            }
        } catch (e: Exception) {
            return "-- error while loading file [$filename]"
        }
    }

    /**
     * 假数据模拟加载配置列表。
     */
    override fun loadLocalConfigs(): List<Script> = fakeData.values.toMutableList()

    override fun loadLocalConfig(id: Long): Script? = fakeData[id]

    override fun updateConfig(id: Long, script: Script) {
        fakeData[id] = script
    }

    // ======= 造假数据 ========



    private fun createAntForest() = Script(
        0,
        "蚂蚁森林领能量",
        "蚂蚁森林领能量\n".repeat(10),
        Random.Default.nextInt(10).toLong(),
        Script.STATE_PAUSED,
        "-- todo"
    )

    /**
     * 加载配置文件`mm_send_msg_sample.lua`。这个脚本往微信文件传输助手发送一条内容为"Hello ClickerX!"的消息
     */
    private fun loadWechatScript() = readAsset("mm_send_msg_sample.lua")

    private fun createSendMessageScript(id: Long): Script {
        return Script(
            id,
            "微信发消息",
            "普通脚本示例：微信向指定用户发送消息。\n\n",
            Random.Default.nextInt(10).toLong(),
            STATE_DEFAULT,
            loadWechatScript()
        )
    }

    private fun createOpenChrome(): Script {
        return Script(
            2L,
            "打开百度搜索",
            "打开百度并搜索ClickerX",
            Random.Default.nextInt(10).toLong(),
            STATE_DEFAULT,
            readAsset("search_sample.lua")
        )
    }

    /**
     * 抢红包脚本
     */
    private fun createRedPacketScript(@Suppress("SameParameterValue") id: Long): Script {
        return Script(
            id = id,
            title = "自动抢红包",
            desc = "触发脚本示例：自动抢红包",
            creatorId = Random.Default.nextInt(10).toLong(),
            state = Script.STATE_SCHEDULED,
            code = readAsset("hongbao_sample.lua"),
            trigger = true
        )
    }
}