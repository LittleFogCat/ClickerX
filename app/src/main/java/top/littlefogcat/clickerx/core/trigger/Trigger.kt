package top.littlefogcat.clickerx.core.trigger

import org.luaj.vm2.LuaClosure
import org.luaj.vm2.lib.jse.CoerceJavaToLua

/**
 * 触发器类。
 *
 * 对于触发执行的脚本来说，需要一个触发器来告知应该在什么情况下执行。
 *
 *
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 * todo 是否应该禁止一个触发脚本在回调函数之外执行任何操作？
 */
abstract class Trigger(val scriptId: Long, val name: String, val type: Int, val params: Map<*, *>, val action: LuaClosure) : Triggerable {
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