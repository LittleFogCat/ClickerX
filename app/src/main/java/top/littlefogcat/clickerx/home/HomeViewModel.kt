package top.littlefogcat.clickerx.home

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.common.base.BaseViewModel
import top.littlefogcat.clickerx.db.entities.RecommendItem
import top.littlefogcat.clickerx.db.entities.RecommendSearchItem
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
        runOnIO {
            val result = recommendRepository.getRecommendList()
            recommendList.postValue(result)
        }
    }

    fun loadRecommendSearch() {
        runOnIO {
            val result = recommendRepository.getRecommendSearchList()
            val idx = Random.Default.nextInt(result.size)
            searchList.postValue(result)
            searchHint.postValue(result[idx])
        }
    }

}