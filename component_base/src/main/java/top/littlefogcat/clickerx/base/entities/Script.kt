package top.littlefogcat.clickerx.base.entities

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import top.littlefogcat.common.utils.Resource
import top.littlefogcat.clickerx.base.R
import top.littlefogcat.clickerx.base.entities.Script.Companion.STATE_DEFAULT
import top.littlefogcat.clickerx.base.entities.Script.Companion.STATE_PAUSED
import top.littlefogcat.clickerx.base.entities.Script.Companion.STATE_SCHEDULED

/**
 * 脚本对应实体类。
 *
 * 脚本分为普通脚本和触发脚本。
 *
 * @param id         脚本id
 * @param title      脚本标题
 * @param desc       脚本描述
 * @param creator  脚本作者id
 * @param state      脚本运行状态；默认（没有预定）-[STATE_DEFAULT]；预定运行-[STATE_SCHEDULED]；预定了但暂停运行-[STATE_PAUSED]
 * @param code       脚本lua代码
 * @param trigger    标记该脚本是否为触发脚本。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-1:13
 * @Email littlefogcat@foxmail.com
 */
@Entity(tableName = "script")
data class Script constructor(
    @PrimaryKey val id: Long,
    val title: String,
    val desc: String,
    val creatorId: Long,
    var state: Int,
    var code: String,
    var trigger: Boolean = false
) : BaseObservable() {
    constructor() : this(0L, "", "", 0, 0, "")

    companion object {
        /**
         * 默认状态
         */
        const val STATE_DEFAULT = 0

        /**
         * 只有触发脚本有这个状态！
         * 如果启动了触发器，就是这个状态！
         */
        const val STATE_SCHEDULED = 1
        const val STATE_PAUSED = 2
    }

    fun stateToString(): String {
        return when (state) {
            STATE_DEFAULT -> Resource.getString(R.string.state_unscheduled)
            STATE_SCHEDULED -> Resource.getString(R.string.state_activated)
            STATE_PAUSED -> Resource.getString(R.string.state_paused)
            else -> Resource.getString(R.string.state_unscheduled)
        }
    }

    override fun toString(): String {
        return "{id=$id, desc=${desc.substring(0..10)}, state=$state}"
    }

}

