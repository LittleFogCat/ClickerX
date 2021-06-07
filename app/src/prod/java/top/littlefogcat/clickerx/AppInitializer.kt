package top.littlefogcat.clickerx

import android.app.Application
import androidx.room.Room
import top.littlefogcat.clickerx.model.AppDatabase
import top.littlefogcat.clickerx.model.ClickerXDatabase

/**
 * @Author littlefogcat
 * @Email littlefogcat@foxmail.com
 */
object AppInitializer {

    fun init(application: Application) {
        super.onCreate()
        ClickerXDatabase.db = Room.databaseBuilder(
            application, AppDatabase::class.java, "clickerx"
        ).build()
    }

}