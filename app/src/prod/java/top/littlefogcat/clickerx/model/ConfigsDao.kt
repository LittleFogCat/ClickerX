package top.littlefogcat.clickerx.model

import androidx.room.Dao
import androidx.room.Query

/**
 * @Author littlefogcat
 * @Date 2020/8/4-2:23
 * @Email littlefogcat@foxmail.com
 */
@Dao
interface ConfigsDao {
    @Query("SELECT * FROM config")
    fun loadConfigs(): List<Config>

}