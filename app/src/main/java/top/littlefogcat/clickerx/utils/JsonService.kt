package top.littlefogcat.clickerx.utils

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.R
import java.lang.reflect.Type

/**
 * 用于ARouter自定义对象传递
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Route(path = ROUTE_JSON_SERVICE)
class JsonService : SerializationService {
    override fun init(context: Context?) {
    }

    override fun <T : Any?> json2Object(input: String, clazz: Class<T>): T {
        return GsonUtil.fromJson(input, clazz)
    }

    override fun object2Json(instance: Any): String {
        return GsonUtil.toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String, clazz: Type): T {
        return GsonUtil.fromJson(input, clazz)
    }
}

object ARouterCompat {
    fun navigateTo(target: String, context: Context? = null) {
        ARouter.getInstance()
            .build(target)
            .navigation(context)
    }
}