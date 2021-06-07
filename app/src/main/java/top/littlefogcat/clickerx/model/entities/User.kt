package top.littlefogcat.clickerx.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户基本信息
 *
 * [id] 用户id
 * [username] 用户昵称
 * [avatar] 用户头像
 * [desc] 用户简介
 * [age] 用户年龄
 * [sex] 用户性别
 * [priority] 用户身份，普通用户、管理员、系统等
 * [follower] 关注者数量
 * [release] 发布数量
 * [vote] 收获点赞数
 * [star] 被收藏数
 * [tags] 标签
 * [points] 积分
 */
@Entity
data class User(
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
    val tags: List<String>? = null,
    val points: Int = 0
)