package top.littlefogcat.clickerx.entities

/**
 * 一个[Task]对应一个任务。
 *
 * 作者：littlefogcat
 * 创建日期：2020/8/12-22:36
 * Email：littlefogcat@foxmail.com
 */
class Task(
    val type: Int,
    val commandList: List<Command>,
    var enabled: Boolean
)

const val TASK_TYPE_TIMED = 0
const val TASK_TYPE_NOTIFICATION_TRIGGER = 1
const val TASK_TYPE_MANUAL = 2
