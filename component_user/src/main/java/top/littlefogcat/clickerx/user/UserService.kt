package top.littlefogcat.clickerx.user

import retrofit2.http.*
import top.littlefogcat.clickerx.base.entities.User
import top.littlefogcat.clickerx.base.entities.UserLocal
import top.littlefogcat.clickerx.base.entities.UserSimple
import top.littlefogcat.network.NetResult

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface UserService : UserDataSource {

    @GET("/user/{id}")
    override suspend fun getUser(@Path("id") id: Long): NetResult<User>

    @GET("/user/{id}/simple")
    override suspend fun getUserSimple(@Path("id") id: Long): NetResult<UserSimple>

    @POST("/user/register")
    @FormUrlEncoded
    override suspend fun register(@Field("username") username: String, @Field("password") password: String): NetResult<UserLocal>

    // todo Not safe
    @POST("/user/login")
    @FormUrlEncoded
    override suspend fun login(@Field("username") username: String, @Field("password") password: String): NetResult<UserLocal>

    @POST("/user/logout")
    override suspend fun logout(): NetResult<UserSimple>
}