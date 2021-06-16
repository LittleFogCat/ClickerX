package top.littlefogcat.clickerx.base.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 本地登录用户
 *
 * @param id 用户id
 * @param [username] 用户昵称
 * @param [avatar] 用户头像
 * @param [desc] 用户简介
 * @param [age] 用户年龄
 * @param [sex] 用户性别
 * @param [priority] 用户身份，普通用户、管理员、系统等
 * @param [follower] 关注者数量
 * @param [release] 发布数量
 * @param [vote] 收获点赞数
 * @param [star] 被收藏数
 * @param [tags] 标签
 * @param [points] 积分
 */
@Entity(tableName = "user_local")
data class UserLocal(
    @PrimaryKey val id: Long,
    val username: String,
    val avatar: String,
    val desc: String,
    val age: Int,
    val sex: String,
    val priority: Int = 0,
    val follower: Int = 0,
    val release: Int = 0,
    val vote: Int = 0,
    val star: Int = 0,
    val tags: String? = null,
    val points: Int = 0
)