package top.littlefogcat.clickerx.core

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.floatingactionbutton.FloatingActionButton
import top.littlefogcat.clickerx.App
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.core.Script.Companion.STATE_DEFAULT
import top.littlefogcat.clickerx.core.Script.Companion.STATE_PAUSED
import top.littlefogcat.clickerx.core.Script.Companion.STATE_SCHEDULED
import top.littlefogcat.clickerx.model.entities.UserSimple
import top.littlefogcat.clickerx.utils.Resource

/**
 * 脚本对应实体类。
 *
 * 脚本分为普通脚本和触发脚本。
 *
 * @param state 配置状态；
 * 默认（没有预定）-[STATE_DEFAULT]；
 * 预定运行-[STATE_SCHEDULED]；
 * 预定了但暂停运行-[STATE_PAUSED]
 *
 * @param trigger 标记该脚本是否为触发脚本。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-1:13
 * @Email littlefogcat@foxmail.com
 */
@Entity(tableName = "script_config")
data class Script constructor(
    @PrimaryKey val id: Long,
    val title: String,
    val desc: String,
    val creator: UserSimple,
    var state: Int,
    var code: String,
    var trigger: Boolean = false
) : BaseObservable() {
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

@BindingAdapter("srcByState")
fun setDrawableByScriptState(button: FloatingActionButton, script: Script) {
    val imgRes: Int
    val colorRes: Int
    if (script.state == STATE_SCHEDULED) {
        imgRes = R.drawable.ic_stop
        colorRes = R.color.scriptButtonPause
    } else {
        imgRes = R.drawable.ic_play
        colorRes = R.color.scriptButtonStart
    }
    button.setImageResource(imgRes)
    val colorState = ContextCompat.getColorStateList(App.get(), colorRes)
    button.backgroundTintList = colorState
}

@BindingAdapter("colorByState")
fun setConfigButtonByState(v: View, state: Int) {
    val color = when (state) {
        STATE_SCHEDULED -> R.color.configStatusRunning
        STATE_DEFAULT -> R.color.configStatusOnce
        STATE_PAUSED -> R.color.configStatusPaused
        else -> R.color.white
    }
    v.setBackgroundResource(color)
}