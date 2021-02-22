package top.littlefogcat.clickerx.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 *
 * @Author littlefogcat
 * @Date 2020/8/4-1:13
 * @Email littlefogcat@foxmail.com
 */
@Entity
data class Config(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String
)