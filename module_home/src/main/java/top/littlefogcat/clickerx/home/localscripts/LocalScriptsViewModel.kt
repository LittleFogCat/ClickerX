package top.littlefogcat.clickerx.home.localscripts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clickerx.base.base.BaseViewModel
import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.script.ScriptDataSource
import top.littlefogcat.clinj.Inject

/**
 * @Author：littlefogcat
 * @Date：2021/2/23-2:59
 * @Email：littlefogcat@foxmail.com
 */
class LocalScriptsViewModel : BaseViewModel() {
    @top.littlefogcat.clinj.Inject(FlavorConstants.NAME_SCRIPT_DS)
    lateinit var repository: ScriptDataSource

    val configs = MutableLiveData<List<Script>>()

    fun loadLocalConfigs() {
        runOnIO {
            val configList = repository.loadLocalScripts()
                .also {
                    Log.d(TAG, "loadLocalScripts: $it")
                }
                .sortedBy {
                    it.state
                }
            configs.postValue(configList)
        }
    }

    companion object {
        const val TAG = "LocalScriptsViewModel"
    }
}