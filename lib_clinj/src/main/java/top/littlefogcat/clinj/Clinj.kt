package top.littlefogcat.clinj

import android.content.Context
import android.util.Log
import java.lang.reflect.Field

/**
 * 依赖注入小工具。
 *
 * A dependency injection util.
 *
 * todo Use annotation processor instead of runtime annotation.
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object Clinj {
    const val TAG = "Clinj"
    private const val FIELD_FLAVOR = "FLAVOR"
    private lateinit var flavor: String

    /**
     * key - category
     *
     * value - Classes contained in the category
     */
    private val injectMap = HashMap<String, Class<*>>()

    fun register(context: Context) {
        flavor = resolveFlavor(context.packageName)
        val files = ClassUtils.getFileNameByPackageName(context, context.packageName)
        files.forEach { className ->
            val cls = Class.forName(className)
            for (a in cls.annotations) {
                if (a is InjectSrc && a.flavor == flavor) {
                    if (injectMap.containsKey(a.name)) {
                        val warning = "Multiple @${Inject::class.java.simpleName} annotations named `${a.name}` " +
                                "are set to class [${injectMap[a.name]?.name ?: "null"}] and [${cls.name}]. " +
                                "Only one will work and the other will be ignored.\nIs that what you really want?"
                        Log.i(TAG, warning)
                    }
                    injectMap[a.name] = cls
                }
            }
        }
    }

    /**
     * 通过反射获取当前的Flavor
     */
    @Throws(Exception::class)
    private fun resolveFlavor(pkgName: String): String {
        val buildConfigClassName = "$pkgName.BuildConfig"
        val clazz = Class.forName(buildConfigClassName)
        val flavorField = clazz.getDeclaredField(FIELD_FLAVOR)
        val flavor = flavorField.get(null)
        return flavor as String
    }

    fun inject(obj: Any) {
        val cls = obj.javaClass
        for (field in cls.fields) {
            for (a in field.annotations) {
                if (a !is Inject) continue
                try {
                    inject(obj, a.name, field)
                    Log.v(TAG, "inject success: obj=[$obj], field=[${field.get(obj)}]")
                } catch (e: Exception) {
                    Log.e(TAG, "Inject failed for { obj=[$obj], field=[${field.name}], name=[${a.name}]}. Check annotations.", e)
                }
            }
        }
    }

    /**
     * 将[injectMap]中key为[name]的类对象注入到对象[obj]的成员变量[field]中。
     */
    private fun inject(obj: Any, name: String, field: Field) {
        val injectedClass = injectMap[name] ?: return

        val injectObj = try {
            // If it is `object`
            injectedClass.getField("INSTANCE").get(null)!!
        } catch (e: Exception) {
            // Else try to create a new instance of the class
            injectedClass.newInstance()
        }
        field.isAccessible = true
        field.set(obj, injectObj)
    }

}