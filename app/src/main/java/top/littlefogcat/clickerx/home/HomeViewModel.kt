package top.littlefogcat.clickerx.home

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.base.BaseViewModel
import top.littlefogcat.clickerx.model.entities.RecommendItem
import top.littlefogcat.clickerx.model.entities.RecommendSearchItem
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
            searchList.postValue(_searchList)
            searchHint.postValue(_searchList[idx])
        }
    }

}