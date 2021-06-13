package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.db.entities.UserSimple
import top.littlefogcat.clickerx.db.entities.User
import top.littlefogcat.clickerx.db.entities.UserLocal

interface UserDataSource {
    /**
     * 获取当前登录的用户
     */
    fun getCurrentLoginUser(): UserLocal

    fun getUser(id: Long): User

    fun getUserSimple(id: Long): UserSimple
}