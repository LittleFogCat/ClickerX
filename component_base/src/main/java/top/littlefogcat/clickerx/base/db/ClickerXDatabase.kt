package top.littlefogcat.clickerx.base.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import top.littlefogcat.clickerx.base.db.dao.ScriptDao
import top.littlefogcat.clickerx.base.db.dao.UserDao
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clickerx.base.entities.UserLocal

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Database(entities = [Script::class, UserLocal::class], version = 1, exportSchema = false)
abstract class ClickerXDatabase : RoomDatabase() {

    companion object {
        private lateinit var db: ClickerXDatabase

        fun init(a: Application) {
            db = Room.databaseBuilder(
                a,
                ClickerXDatabase::class.java,
                "clickerx_db"
            ).allowMainThreadQueries().build()
        }

        fun get(): ClickerXDatabase {
            return db
        }
    }

    abstract fun getScriptDao(): ScriptDao

    abstract fun getUserDao(): UserDao

}