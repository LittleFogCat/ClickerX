package top.littlefogcat.clickerx.utils

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.AbstractDataBindingAdapter

private const val TAG = "BindAdapters"

@Suppress("UNCHECKED_CAST")
@BindingAdapter("recycler_data")
fun <T> setDataBindingRecyclerViewAdapterData(rv: RecyclerView, data: List<T>?) {
    Log.v(TAG, "setDataBindingRecyclerViewAdapterData: data == $data")
    val adapter = rv.adapter
    if (adapter !is AbstractDataBindingAdapter<*>) {
        Log.w(TAG, "setDataBindingRecyclerViewAdapterData: adapter must be an instance of AbstractDataBindingAdapter's subclass")
        return
    }
    (adapter as AbstractDataBindingAdapter<T>).setData(data)
}

@BindingAdapter("imageUrl")
fun setImageUrl(iv: ImageView, url: String) {
    iv.setImageResource(R.drawable.ic_tab_message_round)
}