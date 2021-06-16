package top.littlefogcat.clickerx.home.me

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import top.littlefogcat.clickerx.base.base.BaseDataBindingListAdapter
import top.littlefogcat.clickerx.base.constants.RouteConstants
import top.littlefogcat.clickerx.home.R
import top.littlefogcat.clickerx.home.databinding.MeListItemHeaderBinding
import top.littlefogcat.clickerx.home.databinding.MeListItemPlainBinding
import top.littlefogcat.common.utils.ARouterHelper

/**
 * Adapter for ListView in [MeFragment].
 *
 * @Author：littlefogcat
 * @Date：2021/2/26-21:31
 * @Email：littlefogcat@foxmail.com
 */
class MeAdapter(context: Context) : BaseDataBindingListAdapter<MeListItem>() {
    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.type
    }

    override fun getViewTypeCount(): Int {
        return 3
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val item = getItem(position)
        when (val viewType = getItemViewType(position)) {
            MeListItem.TYPE_PLAIN -> {
                val binding: MeListItemPlainBinding
                if (view == null) {
                    binding = DataBindingUtil.inflate(inflater, R.layout.me_list_item_plain, parent, false)
                    view = binding.root
                    view.tag = binding
                } else {
                    binding = view.tag as MeListItemPlainBinding
                }
                if (item.target != null)
                    view.setOnClickListener {
                        ARouterHelper.navigateTo(RouteConstants.ROUTE_ACTIVITY_DUMMY) // TODO: Navigate to specified Activity
                    }
                binding.item = item as MeListItemPlain
                binding.executePendingBindings()
            }
            MeListItem.TYPE_HEADER -> {
                val binding: MeListItemHeaderBinding
                if (view == null) {
                    binding = DataBindingUtil.inflate(inflater, R.layout.me_list_item_header, parent, false)
                    view = binding.root
                    view.tag = binding
                } else {
                    binding = view.tag as MeListItemHeaderBinding
                }
                if (item.target != null)
                    view.setOnClickListener {
                        ARouterHelper.navigateTo(RouteConstants.ROUTE_ACTIVITY_DUMMY) // TODO: Navigate to specified Activity
                    }
                binding.item = item as MeListItemHeader
                binding.executePendingBindings()
            }
            MeListItem.TYPE_DECORATOR -> {
                return view ?: inflater.inflate(R.layout.me_list_decorator, parent, false)
            }
            else -> throw RuntimeException("Illegal view type $viewType")
        }
        return view
    }

}

