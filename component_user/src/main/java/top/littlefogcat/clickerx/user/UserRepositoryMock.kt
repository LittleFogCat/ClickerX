package top.littlefogcat.clickerx.user

import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.User
import top.littlefogcat.clickerx.base.entities.UserLocal
import top.littlefogcat.clickerx.base.entities.UserSimple
import top.littlefogcat.clickerx.base.utils.Mocker
import top.littlefogcat.clinj.InjectSrc
import top.littlefogcat.network.NetResult
import kotlin.random.Random

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@InjectSrc(FlavorConstants.NAME_USER_DS, FlavorConstants.FLAVOR_MOCK)
object UserRepositoryMock : UserDataSource {
    private val fakeLocalUser = UserLocal(1, "littlefogcat", "", "Keep learning", 18, "男")

    override suspend fun getCurrentLoginUser(): UserLocal {
        return fakeLocalUser
    }

    override suspend fun getUser(id: Long): NetResult<User> {
        return NetResult.Success(User(id, "niuu", "", "Keep learning", 18, "男"))
    }

    override suspend fun getUserSimple(id: Long): NetResult<UserSimple> {
        return NetResult.Success(createUserSimple(id))
    }

    override suspend fun register(username: String, password: String): NetResult<UserLocal> {
        return NetResult.Success(fakeLocalUser)
    }

    override suspend fun login(username: String, password: String): NetResult<UserLocal> {
        return NetResult.Success(fakeLocalUser)
    }

    override suspend fun logout(): NetResult<UserSimple> {
        return NetResult.Success(UserSimple(fakeLocalUser))
    }

    private fun createUserSimple(id: Long): UserSimple {
        val r = Random.Default.nextInt(10)
        val name = when {
            r > 8 -> "BigFogCat"
            r > 5 -> "Niuu"
            else -> "littlefogcat"
        }
        return UserSimple(id, name, Mocker.avatar())
    }
}