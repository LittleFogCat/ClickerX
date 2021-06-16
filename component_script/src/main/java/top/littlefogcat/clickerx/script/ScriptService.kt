package top.littlefogcat.clickerx.script

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.network.NetResult

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface ScriptService : ScriptDataSource {
    @GET("/script/{id}")
    override suspend fun getRemoteScript(@Path("id") id: Long): NetResult<Script>

    @POST("/script/{id}")
    override suspend fun updateRemoteScript(@Path("id") id: Long, @Body script: Script): NetResult<Any?>
}