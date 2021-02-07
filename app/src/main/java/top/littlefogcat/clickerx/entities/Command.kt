package top.littlefogcat.clickerx.entities

/**
 * 一个[Command]对应一条指令。
 *
 * 例如"打开包名为top.littlefogcat.clickerx的应用"为一条指令，
 * 其对应的[action]为"launchPackage"，[param]为"top.littlefogcat.clickerx"，
 * 对应的json字符串则是：
 * ```
 * {
 *     "action":"launchPackage",
 *     "param": "top.littlefogcat.clickerx"
 * }
 * ```
 *
 * @Author：littlefogcat
 * @Date：2020/8/12-22:38
 * @Email：littlefogcat@foxmail.com<br>
 */
class Command(val action: String, val param: String)

const val ACTION_LAUNCH_PACKAGE = "launchPackage"
const val ACTION_CLICK_BY_TEXT = "clickByText"
const val WAIT = "wait"