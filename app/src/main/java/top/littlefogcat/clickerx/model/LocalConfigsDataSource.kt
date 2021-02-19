package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.model.entities.Config

/**
 * 配置文件的数据源，提供了加载配置文件的功能。
 * 按照具体实现，它可以是数据库、网络或者假数据等。
 * 由[Injector.provideLocalConfigsDataSource]提供具体实现对象。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-16:29
 * @Email littlefogcat@foxmail.com
 */
interface LocalConfigsDataSource {
    /**
     * 加载配置文件列表。
     */
    fun loadConfigs(): List<Config>
}