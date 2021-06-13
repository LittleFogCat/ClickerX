package top.littlefogcat.clickerx.core.trigger

import org.luaj.vm2.LuaClosure
import org.luaj.vm2.lib.jse.CoerceJavaToLua

/**
 * 触发器类。
 *
 * 对于触发执行的脚本来说，需要一个触发器来告知应该在什么情况下执行。
 * 这个条件在lua中通过一个table指定，并通过`setTrigger(name, type, args, action)`函数通过参数[params]传递到Java层。
 *
 * Lua代码：
 * ```lua
 * local params = { pkgName = "top.littlefogcat.sample", title = "测试标题", content = "测试内容" }
 * local action = function()
 *     print("检测到触发器触发")
 * end
 * setTrigger("testTrigger", TRIGGER_TYPE_NOTIFICATION, params, action)
 * ```
 *
 * 如果不设置对应的参数，则代表不过滤该参数。例如，对于一个NotificationTrigger类型的触发器，如果不设置`content`参数，
 * 则不筛选内容文字。
 *
 * 当成功触发，调用[onTrigger]回调。其`payload`参数表示需要返回给Lua脚本中的数据。
 *
 * @param scriptId 该触发器所属脚本id
 * @param name 触发器名字，自定义，只作为演示使用，不作为唯一标识符。
 * @param type 触发器类型，暂时只有[Trigger.TYPE_NOTIFICATION]一种。
 * @param params 触发器所需参数。详见各触发器实现类。
 * @param action 成功触发之后执行的操作。
 *
 * @see [NotificationTrigger]
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 * todo 是否应该禁止一个触发脚本在回调函数之外执行任何操作？
 */
abstract class Trigger(
    val scriptId: Long,
    val name: String,
    val type: Int,
    val params: Map<*, *>,
    val action: LuaClosure
) : Triggerable {
    companion object {
        const val TYPE_TIME = 0
        const val TYPE_TIME_REPEATING = 1
        const val TYPE_NOTIFICATION = 2
    }

    override fun onTrigger(payload: Any?) {
        if (payload == null) {
            action.invoke()
        } else {
            val luaObj = CoerceJavaToLua.coerce(payload)
            action.invoke(luaObj)
        }
    }
}