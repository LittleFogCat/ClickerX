package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.User
import top.littlefogcat.clickerx.model.entities.UserLocal

interface UserDataSource {
    /**
     * 获取当前登录的用户
     */
    fun getCurrentLoginUser(): UserLocal

    fun getUser(id: Long): User
}