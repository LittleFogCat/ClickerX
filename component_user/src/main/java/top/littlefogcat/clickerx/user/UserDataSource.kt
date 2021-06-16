package top.littlefogcat.clickerx.user

import top.littlefogcat.clickerx.base.entities.User
import top.littlefogcat.clickerx.base.entities.UserLocal
import top.littlefogcat.clickerx.base.entities.UserSimple
import top.littlefogcat.network.NetResult


interface UserDataSource {
    /**
     * 获取当前登录的用户
     */
    suspend fun getCurrentLoginUser(): UserLocal

    suspend fun getUser(id: Long): NetResult<User>

    suspend fun getUserSimple(id: Long): NetResult<UserSimple>

    suspend fun register(username: String, password: String): NetResult<UserLocal>

    suspend fun login(username: String, password: String): NetResult<UserLocal>

    suspend fun logout(): NetResult<UserSimple>

}