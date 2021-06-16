package top.littlefogcat.clickerx.scriptdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.base.base.BaseViewModel
import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clickerx.base.entities.UserSimple
import top.littlefogcat.clickerx.core.Core
import top.littlefogcat.clickerx.script.ScriptDataSource
import top.littlefogcat.clickerx.user.UserRepositoryMock
import top.littlefogcat.clinj.Inject

/**
 * 单个脚本详情对应的ViewModel。
 *
 * 提供的功能包括
 * [scheduleScript] 定时执行脚本；
 * [updateScript] 编辑脚本。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class ScriptDetailViewModel : BaseViewModel() {
    @top.littlefogcat.clinj.Inject(FlavorConstants.NAME_SCRIPT_DS)
    lateinit var configRepository: ScriptDataSource
    val userRepository = UserRepositoryMock
    val script = MutableLiveData<Script>()
    val creator = MutableLiveData<UserSimple>()

    // avoid to use `getCreator` as function name cause there's already a variable named `creator`
    fun fetchCreator() {
        runOnIO {
            val userId = script.value?.creatorId ?: return@runOnIO
            userRepository.getUserSimple(userId).resolveIfSuccess { userSimple ->
                Log.d(TAG, "getCreator: $userSimple")
                creator.postValue(userSimple)
            }
        }
    }

    fun runScriptOrPause() {
        val script = this.script.value ?: return

        if (script.state == Script.STATE_SCHEDULED) {
            Core.unScheduleScript(script)
            script.state = Script.STATE_DEFAULT
            script.notifyChange()
        } else runOnIO {
            Core.runScript(script)
            if (script.trigger) {
                script.state = Script.STATE_SCHEDULED
                script.notifyChange()
            }
        }
    }

    fun scheduleScript() {
        // TODO: 定时执行
    }

    fun updateScript(code: String) {
        val cfg = script.value
        if (cfg != null) {
            cfg.code = code
            runOnIO {
                configRepository.updateLocalScript(cfg.id, cfg)
            }
        }
    }
}