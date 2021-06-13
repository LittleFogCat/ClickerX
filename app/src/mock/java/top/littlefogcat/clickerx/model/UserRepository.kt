package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.Mocker
import top.littlefogcat.clickerx.db.entities.UserSimple
import top.littlefogcat.clickerx.db.entities.User
import top.littlefogcat.clickerx.db.entities.UserLocal
import kotlin.random.Random

class UserRepository : UserDataSource {
    override fun getCurrentLoginUser(): UserLocal {
        return UserLocal(1, "littlefogcat", "", "Keep learning", 18, "男")
    }

    override fun getUser(id: Long): User {
        return User(id, "niuu", "", "Keep learning", 18, "男")
    }

    override fun getUserSimple(id: Long): UserSimple {
        return createUserSimple(id)
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