package top.littlefogcat.clickerx.model

import androidx.room.Dao
import androidx.room.Query

/**
 * @Author littlefogcat
 * @Email littlefogcat@foxmail.com
 */
@Dao
interface ConfigsDao {
    @Query("SELECT * FROM config")
    fun loadConfigs(): List<Config>

}