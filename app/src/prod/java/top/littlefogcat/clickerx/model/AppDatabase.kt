package top.littlefogcat.clickerx.model

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @Author littlefogcat
 * @Email littlefogcat@foxmail.com
 */
@Database(entities = [Config::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getConfigsDao(): ConfigsDao
}