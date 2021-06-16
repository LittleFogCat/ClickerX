package top.littlefogcat.clickerx.script

import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.network.NetResult
import top.littlefogcat.network.RetrofitCreator
import top.littlefogcat.clickerx.base.db.ClickerXDatabase
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clinj.InjectSrc

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@InjectSrc(FlavorConstants.NAME_SCRIPT_DS, FlavorConstants.FLAVOR_PROD)
object ScriptRepositoryProd : ScriptDataSource {
    private val remote by lazy { RetrofitCreator.create(ScriptService::class.java) }
    private val local = ClickerXDatabase.get().getScriptDao()

    override suspend fun loadLocalScripts(): List<Script> {
        return local.getAllScripts()
    }

    override suspend fun loadLocalScript(id: Long): Script {
        return local.getScript(id)
    }

    override suspend fun updateLocalScript(id: Long, script: Script): Int {
        return local.updateScript(script)
    }

    override suspend fun getRemoteScript(id: Long): NetResult<Script> {
        return remote.getRemoteScript(id)
    }

    override suspend fun updateRemoteScript(id: Long, script: Script): NetResult<Any?> {
        return remote.updateRemoteScript(id, script)
    }
}