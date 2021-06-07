package top.littlefogcat.clickerx.core.trigger

import org.luaj.vm2.LuaClosure
import org.luaj.vm2.lib.jse.CoerceJavaToLua
import java.util.*

/**
 * 通知触发器。
 *
 * 当通知到来时，如果其满足包名为[pkgName]，标题为[title]，内容为[content]，那么就触发成功。
 *
 *
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class NotificationTrigger(scriptId: Long, name: String, params: Map<*, *>, action: LuaClosure) :
    Trigger(scriptId, name, TYPE_NOTIFICATION, params, action)