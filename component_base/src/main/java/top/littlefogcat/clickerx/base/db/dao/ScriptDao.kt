package top.littlefogcat.clickerx.base.db.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import top.littlefogcat.clickerx.base.entities.Script

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Dao
interface ScriptDao {
    @Query("select * from script")
    fun getAllScripts(): List<Script>

    @Query("select * from script where id = :id")
    fun getScript(id: Long): Script

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateScript(script: Script): Int

}