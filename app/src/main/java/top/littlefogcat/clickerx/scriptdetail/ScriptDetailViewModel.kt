package top.littlefogcat.clickerx.scriptdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.common.base.BaseViewModel
import top.littlefogcat.clickerx.core.Core
import top.littlefogcat.clickerx.db.entities.Script
import top.littlefogcat.clickerx.db.entities.UserSimple

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
    val configRepository = Injector.provideConfigsDataSource()
    val userRepository = Injector.provideUserDataSource()
    val script = MutableLiveData<Script>()
    val creator = MutableLiveData<UserSimple>()

    // avoid to use `getCreator` as function name cause there's already a variable named `creator`
    fun fetchCreator() {
        runOnIO {
            Log.d(TAG, "getCreator: script=${script.value}")
            val userId = script.value?.creatorId ?: return@runOnIO
            val userSimple = userRepository.getUserSimple(userId)
            Log.d(TAG, "getCreator: $userSimple")
            creator.postValue(userSimple)
        }
    }

    fun runScriptOrPause() {
        val scr = script.value ?: return

        if (scr.state == Script.STATE_SCHEDULED) {
            Core.unScheduleScript(scr)
            scr.state = Script.STATE_DEFAULT
            scr.notifyChange()
        } else runOnIO {
            Core.runScript(scr)
            if (scr.trigger) {
                scr.state = Script.STATE_SCHEDULED
                scr.notifyChange()
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
            configRepository.updateConfig(cfg.id, cfg)
        }
    }
}