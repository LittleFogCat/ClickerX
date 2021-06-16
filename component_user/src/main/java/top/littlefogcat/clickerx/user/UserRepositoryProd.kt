package top.littlefogcat.clickerx.user

import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.db.ClickerXDatabase
import top.littlefogcat.clickerx.base.entities.User
import top.littlefogcat.clickerx.base.entities.UserLocal
import top.littlefogcat.clickerx.base.entities.UserSimple
import top.littlefogcat.clinj.InjectSrc
import top.littlefogcat.network.NetResult
import top.littlefogcat.network.RetrofitCreator

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@InjectSrc(FlavorConstants.NAME_USER_DS, FlavorConstants.FLAVOR_PROD)
object UserRepositoryProd : UserDataSource {
    private val local = ClickerXDatabase.get().getUserDao()
    private val remote by lazy { RetrofitCreator.create(UserService::class.java) }

    override suspend fun getCurrentLoginUser(): UserLocal {
        return local.getCurrentUser()
    }

    override suspend fun getUser(id: Long): NetResult<User> {
        return remote.getUser(id)
    }

    override suspend fun getUserSimple(id: Long): NetResult<UserSimple> {
        return remote.getUserSimple(id)
    }

    override suspend fun register(username: String, password: String): NetResult<UserLocal> {
        return remote.register(username, password).also { result ->
            if (result.isSuccess) {
                local.login(result.data!!)
            }
        }
    }

    override suspend fun login(username: String, password: String): NetResult<UserLocal> {
        return remote.login(username, password).also { result ->
            if (result.isSuccess) {
                local.login(result.data!!)
            }
        }
    }

    override suspend fun logout(): NetResult<UserSimple> {
        return remote.logout().also {
            if (it.isSuccess) {
                local.logout(it.data!!.id)
            }
        }
    }

}