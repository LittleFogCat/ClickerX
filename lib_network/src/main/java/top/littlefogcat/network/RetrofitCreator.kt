package top.littlefogcat.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Helper class for creating Retrofit service.
 *
 * todo add base url
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object RetrofitCreator {
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("") // todo
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}