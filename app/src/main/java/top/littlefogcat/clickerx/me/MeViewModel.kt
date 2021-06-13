package top.littlefogcat.clickerx.me

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.async
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.common.base.BaseViewModel
import top.littlefogcat.clickerx.common.utils.ROUTE_ACTIVITY_DUMMY

/**
 * @Author：littlefogcat
 * @Date：2021/2/26-16:55
 * @Email：littlefogcat@foxmail.com
 */
class MeViewModel : BaseViewModel() {
    val userDataSource = Injector.provideUserDataSource()
    val listData = MutableLiveData<List<MeListItem>>()

    fun loadData() {
        runOnIO {
            val list = mutableListOf<MeListItem>()
            val header = async {
                userDataSource.getCurrentLoginUser()
            }
            val currentUser = header.await()
            list.add(MeListItemHeader(currentUser.avatar, currentUser.username, currentUser.desc, ROUTE_ACTIVITY_DUMMY))
            list.addAll(getBody())
            listData.postValue(list)
        }
    }

    private fun getBody(): List<MeListItem> {
        return arrayListOf(
            MeListItemDecorator,
            MeListItemPlain("", "赚积分", ROUTE_ACTIVITY_DUMMY),
            MeListItemPlain("", "设置", ROUTE_ACTIVITY_DUMMY),
            MeListItem.DIVIDER,
            MeListItemPlain("", "退出登录", ROUTE_ACTIVITY_DUMMY),
        )
    }

}