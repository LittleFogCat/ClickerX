package top.littlefogcat.clickerx.home.me

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.async
import top.littlefogcat.clickerx.base.base.BaseViewModel
import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.constants.RouteConstants
import top.littlefogcat.clickerx.user.UserDataSource
import top.littlefogcat.clinj.Inject

/**
 * @Author：littlefogcat
 * @Date：2021/2/26-16:55
 * @Email：littlefogcat@foxmail.com
 */
class MeViewModel : BaseViewModel() {
    @top.littlefogcat.clinj.Inject(FlavorConstants.NAME_USER_DS)
    lateinit var userDataSource:UserDataSource
    val listData = MutableLiveData<List<MeListItem>>()

    fun loadData() {
        runOnIO {
            val list = mutableListOf<MeListItem>()
            val header = async {
                userDataSource.getCurrentLoginUser()
            }
            val currentUser = header.await()
            list.add(MeListItemHeader(currentUser.avatar, currentUser.username, currentUser.desc, RouteConstants.ROUTE_ACTIVITY_DUMMY))
            list.addAll(getBody())
            Log.d(TAG, "loadData: $list")
            listData.postValue(list)
        }
    }

    private fun getBody(): List<MeListItem> {
        return arrayListOf(
            MeListItemDecorator,
            MeListItemPlain("", "赚积分", RouteConstants.ROUTE_ACTIVITY_DUMMY),
            MeListItemPlain("", "设置", RouteConstants.ROUTE_ACTIVITY_DUMMY),
            MeListItem.DIVIDER,
            MeListItemPlain("", "退出登录", RouteConstants.ROUTE_ACTIVITY_DUMMY),
        )
    }

}