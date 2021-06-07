package top.littlefogcat.clickerx.model

/**
 * 配置文件的数据源的实现类。
 * 从数据库中读取。
 *
 * 作者：littlefogcat
 * 创建日期：2020/8/4-16:41
 * Email：littlefogcat@foxmail.com
 */
class ConfigsRepository : ConfigsDataSource {

    /**
     * 从数据库中加载配置文件列表。如果为空，则返回一个空的[ArrayList]。
     */
    override fun loadConfigs(): List<Config> {
        val configsDao = ClickerXDatabase.db?.getConfigsDao() ?: return arrayListOf()
        return configsDao.loadConfigs()
    }
}