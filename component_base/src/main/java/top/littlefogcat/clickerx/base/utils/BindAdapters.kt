package top.littlefogcat.clickerx.base.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import top.littlefogcat.clickerx.base.R
import top.littlefogcat.clickerx.base.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.base.base.BaseDataBindingListAdapter
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.common.utils.AppContext
import top.littlefogcat.common.utils.ImageLoader

private const val TAG = "BindAdapters"

// 如果提示AAPT: xx not found, 说明没有在build.gradle里开启DataBinding

/**
 * 对于Adapter类型为[BaseDataBindingAdapter]的RecyclerView，调用此方法设置数据。
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("recycler_data")
fun <T> setDataForDataBindingRecyclerViewAdapter(rv: RecyclerView, data: List<T>?) {
    Log.v(TAG, "setDataBindingRecyclerViewAdapterData: data == $data")
    val adapter = rv.adapter
    if (adapter !is BaseDataBindingAdapter<*, *>) {
        Log.w(TAG, "setDataBindingRecyclerViewAdapterData: adapter must be an instance of AbstractDataBindingAdapter's subclass")
        return
    }
    (adapter as BaseDataBindingAdapter<T, *>).setData(data)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("list_data")
fun <T> setDataForDataBindingListViewAdapter(lv: ListView, data: List<T>?) {
    Log.v(TAG, "setDataBindingListViewAdapterData: $data")
    val adapter = lv.adapter
    if (data != null && adapter is BaseDataBindingListAdapter<*>) {
        (adapter as BaseDataBindingListAdapter<T>).setData(data)
    }
}

@BindingAdapter("imageSrc")
fun setImageUrl(iv: ImageView, url: String?) {
    if (url != null) ImageLoader.get().loadImage(iv, url)
}

@BindingAdapter("imageSrc")
fun setImageRes(iv: ImageView, res: Int) {
    ImageLoader.get().loadImage(iv, res)
}

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