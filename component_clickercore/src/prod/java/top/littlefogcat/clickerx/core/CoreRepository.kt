package top.littlefogcat.clickerx.core

import top.littlefogcat.clickerx.db.ClickerXDatabase
import top.littlefogcat.clickerx.db.entities.Script

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object CoreRepository {
    private val scriptDao by lazy { ClickerXDatabase.get().getScriptDao() }

    fun loadLocalScripts(): List<Script> {
        return scriptDao.getAllScripts()
    }

    fun loadScript(id: Long) = scriptDao.getScript(id)

    fun updateScript(script: Script) = scriptDao.updateScript(script)
}