package top.littlefogcat.clickerx.home.home

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.base.base.BaseViewModel
import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.RecommendItem
import top.littlefogcat.clickerx.base.entities.RecommendSearchItem
import top.littlefogcat.clickerx.home.model.datasource.RecommendDataSource
import top.littlefogcat.clinj.Inject
import kotlin.random.Random

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:32
 * @Email：littlefogcat@foxmail.com
 */
class HomeViewModel : BaseViewModel() {
    @top.littlefogcat.clinj.Inject(FlavorConstants.NAME_RECOMMEND_DS)
    lateinit var recommendRepository: RecommendDataSource

    val recommendList = MutableLiveData<List<RecommendItem>>()
    val searchHint = MutableLiveData<RecommendSearchItem>()
    val searchList = MutableLiveData<List<RecommendSearchItem>>()

    fun loadRecommendList() {
        runOnIO {
            recommendRepository.getRecommendList().resolveIfSuccess {
                recommendList.postValue(it)
            }
        }
    }

    fun loadRecommendSearch() {
        runOnIO {
            recommendRepository.getRecommendSearchList().resolveIfSuccess { result ->
                val idx = Random.Default.nextInt(result.size)
                searchList.postValue(result)
                searchHint.postValue(result[idx])
            }
        }
    }

}