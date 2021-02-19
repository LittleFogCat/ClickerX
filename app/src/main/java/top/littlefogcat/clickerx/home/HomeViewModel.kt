package top.littlefogcat.clickerx.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.base.BaseViewModel
import top.littlefogcat.clickerx.model.entities.RecommendItem
import top.littlefogcat.clickerx.model.RecommendSearchItem
import top.littlefogcat.clickerx.utils.TAG
import kotlin.random.Random

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:32
 * @Email：littlefogcat@foxmail.com
 */
class HomeViewModel : BaseViewModel() {
    val recommendRepository = Injector.provideRecommendDataSource()

    val recommendList = MutableLiveData<List<RecommendItem>>()
    val searchHint = MutableLiveData<RecommendSearchItem>()
    val searchList = MutableLiveData<List<RecommendSearchItem>>()

    fun loadRecommendList() {
        runOnIO(recommendList) {
            recommendRepository.getRecommendList()
        }
//        runOnIOThread {
//            val _recommendList = recommendRepository.getRecommendList()
//            withContext(Dispatchers.Main) {
//                Log.d(TAG, "loadRecommendList: $_recommendList")
//                recommendList.value = _recommendList
//            }
//        }
    }

    fun loadRecommendSearch() {
        runOnIO {
            val _searchList = recommendRepository.getRecommendSearchList()
            val idx = Random.Default.nextInt(_searchList.size)
            withContext(Dispatchers.Main) {
                searchList.value = _searchList
                searchHint.value = _searchList[idx]
            }
        }
    }

}