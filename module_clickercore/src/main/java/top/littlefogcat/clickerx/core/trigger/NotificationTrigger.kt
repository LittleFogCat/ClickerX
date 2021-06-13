package top.littlefogcat.clickerx.core.trigger

import org.luaj.vm2.LuaClosure
import java.util.*

/**
 * 通知触发器，Lua中对应的触发器类型为`TRIGGER_TYPE_NOTIFICATION`。
 *
 * 当通知到来时，如果其满足包名为`pkgName`，标题为`title`，内容为`content`，那么就触发成功。
 * 这些参数在[params]中定义。在lua中定义一个table，加入这些参数：
 * ```lua
 * local params = { pkgName = "top.littlefogcat.sample", title = "测试标题", content = "测试内容" }
 * local action = function()
 *     print("检测到触发器触发")
 * end
 * setTrigger("testTrigger", TRIGGER_TYPE_NOTIFICATION, params, action)
 * ```
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class NotificationTrigger(scriptId: Long, name: String, params: Map<*, *>, action: LuaClosure) :
    Trigger(scriptId, name, TYPE_NOTIFICATION, params, action)