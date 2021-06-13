package top.littlefogcat.clickerx.db

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import top.littlefogcat.clickerx.db.dao.ScriptDao
import top.littlefogcat.clickerx.db.entities.Script

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Database(entities = [Script::class], version = 1, exportSchema = false)
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

}