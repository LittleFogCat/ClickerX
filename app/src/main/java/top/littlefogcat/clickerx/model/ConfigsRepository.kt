package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.Injector

/**
 * Model层
 *
 * @Author littlefogcat
 * @Date 2020/8/4-2:23
 * @Email littlefogcat@foxmail.com
 */
class ConfigsRepository {
    private val dataSource = Injector.provideConfigsDataSource()

    fun loadConfigs(): List<Config> {
        return dataSource.loadConfigs()
    }

}