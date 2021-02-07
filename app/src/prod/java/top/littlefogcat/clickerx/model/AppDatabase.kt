package top.littlefogcat.clickerx.model

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @Author littlefogcat
 * @Date 2020/8/4-11:23
 * @Email littlefogcat@foxmail.com
 */
@Database(entities = [Config::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getConfigsDao(): ConfigsDao
}