package top.littlefogcat.clickerx.base.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import top.littlefogcat.clickerx.base.entities.UserLocal

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Dao
interface UserDao {
    @Query("select * from user_local")
    suspend fun getCurrentUser(): UserLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun login(userLocal: UserLocal)

    @Query("delete from user_local where id = :id")
    suspend fun logout(id: Long)
}