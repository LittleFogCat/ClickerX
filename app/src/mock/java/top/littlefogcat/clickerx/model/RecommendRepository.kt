package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.RecommendItem
import top.littlefogcat.clickerx.model.entities.RecommendSearchItem
import top.littlefogcat.clickerx.model.entities.User

class RecommendRepository : RecommendDataSource {
    override fun getRecommendList() = arrayListOf(
        RecommendItem(
            "并夕夕签到领红包",
            User(
                0,
                "小母牛",
                "https://profile.csdnimg.cn/8/3/E/0_zengsidou",
                "nothing",
                18,
                "男"
            ),
            "平嘻嘻领红包自动执行，，每日按时执行。",
            1828,
            14
        ),
        RecommendItem(
            "微信自动回复",
            User(1, "littlefogcat", "", "a cat", 18, "男"),
            "微信自动回复，自动抢红包，指定回复内容，AI自动对话",
            999,
            99
        ),
        RecommendItem(
            "随机点击屏幕",
            User(2, "小羊羊", "", "young", 21, "女"),
            "随机点击屏幕上的某个点，可以指定区域、次数和间隔",
            41,
            0
        ),
        RecommendItem(
            "视频自动点赞",
            User(2, "小羊羊", "", "young", 21, "女"),
            "视频自动点赞",
            41,
            0
        ),
        RecommendItem(
            "视频自动点踩",
            User(2, "小羊羊", "", "young", 21, "女"),
            "视频自动点赞",
            41,
            0
        ),
        RecommendItem(
            "微信自动回复",
            User(1, "littlefogcat", "", "a cat", 18, "男"),
            "微信自动回复，自动抢红包，指定回复内容，AI自动对话",
            999,
            99
        ),
        RecommendItem(
            "随机点击屏幕",
            User(2, "小羊羊", "", "young", 21, "女"),
            "随机点击屏幕上的某个点，可以指定区域、次数和间隔",
            41,
            0
        ),
        RecommendItem(
            "视频自动点赞",
            User(2, "小羊羊", "", "young", 21, "女"),
            "视频自动点赞",
            41,
            0
        ),
        RecommendItem(
            "视频自动点踩",
            User(2, "小羊羊", "", "young", 21, "女"),
            "视频自动点赞",
            41,
            0
        )
    )

    override fun getRecommendSearchList() = arrayListOf(
        RecommendSearchItem(0, "B站被锤", "B站被部分品牌方拉黑，随后股价暴涨"),
        RecommendSearchItem(1, "中国1亿多人就地过年", "全国1亿多人就地过年，大年三十，谢谢每个坚守的你！愿山河无恙，家国皆安！"),
        RecommendSearchItem(2, "外交部发言人集体拜年", "2月11日，外交部发言人集体拜年：“祝各位朋友春节快乐，牛气冲天，牛年大吉！”")
    )
}