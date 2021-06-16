package top.littlefogcat.common.utils

/**
 * @Author：littlefogcat
 * @Date：2021/2/22-23:15
 * @Email：littlefogcat@foxmail.com
 */
object DateUtil {
    fun now(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 计算出时间点[time]与当前时间相隔的天数。
     * 如果[time]在当天之前，返回负数；当天之后，返回正数；如果是当天，则返回0。
     */
    fun dayDiffFromNow(time: Long): Int {
        return dayDiff(now(), time)
    }

    /**
     * 计算两个时间点相差的天数，以[time1]为准，如果[time2]在[time1]的日期之前，返回负数，之后返回正数，同一天返回0。
     */
    fun dayDiff(time1: Long, time2: Long): Int {
        // 将毫秒转换成秒
        val t1: Int = (time1 / 1000).toInt()
        val t2: Int = (time2 / 1000).toInt()
        // 一天86400秒，转换到一天的头一秒
        val sec1 = t1 - t1 % 86400
        val sec2 = t2 - t2 % 86400
        return (sec2 - sec1) / 86400
    }

    /**
     * 返回[time]与当天的距离，以天或年为单位。
     */
    @Suppress("ConvertTwoComparisonsToRangeCheck")
    fun dayDiffFromNowToHuman(time: Long): String {
        val diff = dayDiffFromNow(time) // 天
        return when {
            diff == 2 -> "后天"
            diff == 1 -> "明天"
            diff == 0 -> "今天"
            diff == -1 -> "昨天"
            diff == -2 -> "前天"
            diff >= 365 -> "${diff / 365}年后"
            diff > 0 && diff < 365 -> "${diff}天后"
            diff < 0 && diff > -365 -> "${-diff}天前"
            diff <= -365 -> "${-diff / 365}年前"
            else -> ""
        }
    }
}