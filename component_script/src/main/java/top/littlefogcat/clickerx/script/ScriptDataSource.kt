package top.littlefogcat.clickerx.script

import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.network.NetResult

/**
 * 配置文件的数据源，提供了加载配置文件的功能。
 * 按照具体实现，它可以是数据库、网络或者假数据等。
 * 由[ScriptRepositoryMock]提供具体实现对象。
 *
 * Interface for defining script data source.
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface ScriptDataSource {

    /**
     * Load all scripts from local source.
     */
    suspend fun loadLocalScripts(): List<Script>

    /**
     * Load script from local source where id = [id]
     */
    suspend fun loadLocalScript(id: Long): Script?

    suspend fun updateLocalScript(id: Long, script: Script): Int

    /**
     * Get script by id [id] from remote server.
     */
    suspend fun getRemoteScript(id: Long): NetResult<Script>

    /**
     * Update [script] to remote server.
     * Only the origin owner of the remote script can modify the script.
     */
    suspend fun updateRemoteScript(id: Long, script: Script): NetResult<Any?>
}