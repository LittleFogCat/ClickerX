package top.littlefogcat.clickerx.home

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.model.entities.User

class RecyclerItem {
    val title = MutableLiveData<String>()
    val author = MutableLiveData<User>()
    val summary = MutableLiveData<String>()
    val votes = MutableLiveData<Int>()
    val comments = MutableLiveData<Int>()
}