package top.littlefogcat.clickerx

import android.app.Application
import androidx.room.Room
import top.littlefogcat.clickerx.model.AppDatabase
import top.littlefogcat.clickerx.model.ClickerXDatabase

/**
 * @Author littlefogcat
 * @Date 2020/8/4-16:24
 * @Email littlefogcat@foxmail.com
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ClickerXDatabase.db = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "clickerx"
        ).build()
    }

}