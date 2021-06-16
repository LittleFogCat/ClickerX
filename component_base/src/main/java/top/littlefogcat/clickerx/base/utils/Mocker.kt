package top.littlefogcat.clickerx.base.utils

import kotlin.random.Random

/**
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
object Mocker {
    fun avatar(): String {
        return when (Random.Default.nextInt(10)) {
            0, 1, 2 -> "https://profile.csdnimg.cn/8/3/E/0_zengsidou"
            3, 4 -> "https://upload.jianshu.io/users/upload_avatars/6532223/22b91860-5eda-462e-907b-dae9137cee5c"
            5, 6 -> "https://sf3-ttcdn-tos.pstatp.com/img/user-avatar/bc6bc74e13220d24b4a8f4c95535b63d~300x300.image"
            else -> ""
        }
    }
}