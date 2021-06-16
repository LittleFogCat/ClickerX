package top.littlefogcat.clickerx.home.model.repo

import top.littlefogcat.clickerx.base.constants.FlavorConstants
import top.littlefogcat.clickerx.base.entities.RecommendItem
import top.littlefogcat.clickerx.base.entities.RecommendSearchItem
import top.littlefogcat.clickerx.base.entities.UserSimple
import top.littlefogcat.clickerx.base.utils.Mocker
import top.littlefogcat.clickerx.home.model.datasource.RecommendDataSource
import top.littlefogcat.clinj.InjectSrc
import top.littlefogcat.network.NetResult

@InjectSrc(FlavorConstants.NAME_RECOMMEND_DS, flavor = FlavorConstants.FLAVOR_MOCK)
object RecommendRepositoryMock : RecommendDataSource {
    override suspend fun getRecommendList() = NetResult.Success(
        arrayListOf(
            RecommendItem(
                0,
                "并夕夕签到领红包",
                UserSimple(0, "小母牛", Mocker.avatar()),
                "平嘻嘻领红包自动执行，，每日按时执行。",
                1828,
                14
            ),
            RecommendItem(
                1,
                "微信自动回复",
                UserSimple(1, "littlefogcat", Mocker.avatar()),
                "微信自动回复，自动抢红包，指定回复内容，AI自动对话",
                999,
                99
            ),
            RecommendItem(
                2,
                "随机点击屏幕",
                UserSimple(2, "小羊羊", Mocker.avatar()),
                "随机点击屏幕上的某个点，可以指定区域、次数和间隔",
                41,
                0
            ),
            RecommendItem(
                3,
                "视频自动点赞",
                UserSimple(2, "小羊羊", Mocker.avatar()),
                "视频自动点赞",
                41,
                0
            ),
            RecommendItem(
                4,
                "视频自动点踩",
                UserSimple(2, "小羊羊", Mocker.avatar()),
                "视频自动点赞",
                41,
                0
            ),
            RecommendItem(
                5,
                "微信自动回复",
                UserSimple(1, "littlefogcat", Mocker.avatar()),
                "微信自动回复，自动抢红包，指定回复内容，AI自动对话",
                999,
                99
            ),
            RecommendItem(
                6,
                "随机点击屏幕",
                UserSimple(2, "小羊羊", Mocker.avatar()),
                "随机点击屏幕上的某个点，可以指定区域、次数和间隔",
                41,
                0
            ),
            RecommendItem(
                7,
                "视频自动点赞",
                UserSimple(2, "小羊羊", Mocker.avatar()),
                "视频自动点赞",
                41,
                0
            ),
            RecommendItem(
                8,
                "视频自动点踩",
                UserSimple(2, "小羊羊", Mocker.avatar()),
                "视频自动点赞",
                41,
                0
            )
        )
    )

    override suspend fun getRecommendSearchList(): NetResult<List<RecommendSearchItem>> = NetResult.Success(
        arrayListOf(
            RecommendSearchItem(0, "B站被锤", "B站被部分品牌方拉黑，随后股价暴涨"),
            RecommendSearchItem(1, "中国1亿多人就地过年", "全国1亿多人就地过年，大年三十，谢谢每个坚守的你！愿山河无恙，家国皆安！"),
            RecommendSearchItem(2, "外交部发言人集体拜年", "2月11日，外交部发言人集体拜年：“祝各位朋友春节快乐，牛气冲天，牛年大吉！”")
        )
    )

}