package top.littlefogcat.clickerx.scriptdetail

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.common.utils.AppContext
import top.littlefogcat.clickerx.db.entities.Script


@BindingAdapter("colorByState")
fun setConfigButtonByState(v: View, state: Int) {
    val color = when (state) {
        Script.STATE_SCHEDULED -> R.color.configStatusRunning
        Script.STATE_DEFAULT -> R.color.configStatusOnce
        Script. STATE_PAUSED -> R.color.configStatusPaused
        else -> R.color.white
    }
    v.setBackgroundResource(color)
}

@BindingAdapter("srcByState")
fun setDrawableByScriptState(button: FloatingActionButton, script: Script) {
    val imgRes: Int
    val colorRes: Int
    if (script.state == Script.STATE_SCHEDULED) {
        imgRes = R.drawable.ic_stop
        colorRes = R.color.scriptButtonPause
    } else {
        imgRes = R.drawable.ic_play
        colorRes = R.color.scriptButtonStart
    }
    button.setImageResource(imgRes)
    val colorState = ContextCompat.getColorStateList(AppContext.get(), colorRes)
    button.backgroundTintList = colorState
}