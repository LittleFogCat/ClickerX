package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.User
import top.littlefogcat.clickerx.model.entities.UserLocal

class UserRepository : UserDataSource {
    override fun getCurrentLoginUser(): UserLocal {
        return UserLocal(1, "littlefogcat", "", "Keep learning", 18, "男")
    }

    override fun getUser(id: Long): User {
        return User(id, "niuu", "", "Keep learning", 18, "男")
    }
}