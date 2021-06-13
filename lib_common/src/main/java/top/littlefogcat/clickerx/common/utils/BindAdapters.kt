package top.littlefogcat.clickerx.common.utils

import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import top.littlefogcat.clickerx.common.base.BaseDataBindingAdapter
import top.littlefogcat.clickerx.common.base.BaseDataBindingListAdapter

private const val TAG = "BindAdapters"

// TODO: 将此文件的实现移动到对应类的文件中

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

