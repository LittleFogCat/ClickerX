package top.littlefogcat.clickerx.localscripts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.base.BaseViewModel
import top.littlefogcat.clickerx.core.Script

/**
 * @Author：littlefogcat
 * @Date：2021/2/23-2:59
 * @Email：littlefogcat@foxmail.com
 */
class LocalScriptsViewModel : BaseViewModel() {
    val repository = Injector.provideConfigsDataSource()

    val configs = MutableLiveData<List<Script>>()

    fun loadLocalConfigs() {
        runOnIO {
            val configList = repository.loadLocalConfigs()
                .also {
                    Log.d(TAG, "loadLocalConfigs: $it")
                }
                .sortedBy {
                    it.state
                }
            configs.postValue(configList)
        }
    }

    companion object {
        const val TAG = "LocalConfigsViewModel"
    }
}