package top.littlefogcat.clickerx.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户简单信息，用于[Script]
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Entity
data class UserSimple(
    @PrimaryKey val id: Long,
    val username: String,
    val avatarUrl: String,
)