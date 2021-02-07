package top.littlefogcat.clickerx.model

/**
 * 配置文件数据源实现类（Mock）
 *
 * 作者：littlefogcat
 * 创建日期：2020/8/4-16:41
 * Email：littlefogcat@foxmail.com
 */
class ConfigsDataSourceImpl : ConfigsDataSource {

    /**
     * 从数据库中加载配置文件列表。如果为空，则返回一个空的[ArrayList]。
     */
    override fun loadConfigs(): List<Config> = arrayListOf(
        Config("蚂蚁森林"),
        Config("领积分"),
        Config("拼夕夕签到")
    )
}