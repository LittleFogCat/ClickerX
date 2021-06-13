package top.littlefogcat.clickerx.common.utils

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * 作者：littlefogcat
 * 创建日期：2020/8/12-23:05
 * Email：littlefogcat@foxmail.com
 */
object GsonUtil {
    private val gson = Gson()

    fun <T> fromJson(json: String, classOfT: Class<T>) = gson.fromJson(json, classOfT)
    fun <T> fromJson(json: String, type: Type) = gson.fromJson<T>(json, type)
    fun toJson(obj: Any) = gson.toJson(obj)
}