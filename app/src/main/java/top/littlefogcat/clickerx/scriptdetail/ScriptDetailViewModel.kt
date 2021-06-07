package top.littlefogcat.clickerx.scriptdetail

import androidx.lifecycle.MutableLiveData
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.base.BaseViewModel
import top.littlefogcat.clickerx.core.Core
import top.littlefogcat.clickerx.core.Script

/**
 * 单个脚本详情对应的ViewModel。
 *
 * 提供的功能包括
 * [runScriptOnce] 执行一次脚本；
 * [scheduleScript] 定时执行脚本；
 * [updateScript] 编辑脚本。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class ScriptDetailViewModel : BaseViewModel() {
    val configRepository = Injector.provideConfigsDataSource()
    val config = MutableLiveData<Script>()

    fun runScriptOnce() {
        val cfg = config.value ?: return
        Core.runScript(cfg)
    }

    fun runScriptOrPause() {
        val cfg = config.value ?: return
        if (cfg.state == Script.STATE_SCHEDULED) {
            Core.unScheduleScript(cfg)
            cfg.state = Script.STATE_DEFAULT
            cfg.notifyChange()
        } else {
            Core.runScript(cfg)
            if (cfg.trigger) {
                cfg.state = Script.STATE_SCHEDULED
                cfg.notifyChange()
            }
        }
    }

    fun scheduleScript() {
        // TODO: 定时执行
    }

    fun updateScript(code: String) {
        val cfg = config.value
        if (cfg != null) {
            cfg.code = code
            configRepository.updateConfig(cfg.id, cfg)
        }
    }
}