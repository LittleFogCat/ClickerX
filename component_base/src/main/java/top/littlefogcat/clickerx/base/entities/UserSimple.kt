package top.littlefogcat.clickerx.base.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户简单信息，只包含[id] [username] [avatarUrl]
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Entity
data class UserSimple(
    @PrimaryKey val id: Long,
    val username: String,
    val avatarUrl: String,
) {
    constructor(user: UserLocal) : this(user.id, user.username, user.avatar)

    override fun toString(): String {
        return "UserSimple(id=$id, username='$username', avatarUrl='$avatarUrl')"
    }
}