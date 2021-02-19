package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.model.entities.Config

/**
 * 配置文件数据源实现类（Mock）
 *
 * 作者：littlefogcat
 * 创建日期：2020/8/4-16:41
 * Email：littlefogcat@foxmail.com
 */
class LocalConfigsRepository : LocalConfigsDataSource {

    /**
     * 假数据模拟加载配置列表。
     */
    override fun loadConfigs(): List<Config> = arrayListOf(
        Config(0, "蚂蚁森林"),
        Config(1, "领积分"),
        Config(2, "拼夕夕签到")
    )
}