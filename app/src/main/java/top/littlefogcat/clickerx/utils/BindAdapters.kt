package top.littlefogcat.clickerx.utils

import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.AbstractDataBindingAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("recycler_data")
fun <T> setDataBindingRecyclerViewAdapterData(rv: RecyclerView, data: List<T>?) {
    Log.v(TAG, "setDataBindingRecyclerViewAdapterData: data == $data")
    val adapter = rv.adapter
    if (adapter !is AbstractDataBindingAdapter<*>) {
        throw IllegalArgumentException("Exception/ setData: [data] must be instance of DataBindingAdapter")
    }
    (adapter as AbstractDataBindingAdapter<T>).setData(data)
}

@BindingAdapter("avatar")
fun setAvatar(tv: TextView, avatar: String) {
    tv.setCompoundDrawablesWithIntrinsicBounds(
        tv.resources.getDrawable(R.drawable.ic_action_add),
        null, null, null
    )
}