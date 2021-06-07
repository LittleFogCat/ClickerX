package top.littlefogcat.clickerx.model

import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.core.Script

/**
 * 配置文件的数据源，提供了加载配置文件的功能。
 * 按照具体实现，它可以是数据库、网络或者假数据等。
 * 由[Injector.provideConfigsDataSource]提供具体实现对象。
 *
 * APIs：
 *
 * [loadLocalConfig] 根据id从本地加载对应的配置文件；
 *
 * [loadLocalConfigs] 加载本地保存的所有配置文件；
 *
 * []
 *
 *
 * @Author littlefogcat
 * @Date 2020/8/4-16:29
 * @Email littlefogcat@foxmail.com
 */
interface ScrpitsDataSource {
    /**
     * 加载配置文件列表。
     */
    fun loadLocalConfigs(): List<Script>


    /**
     * 加载本地配置文件
     */
    fun loadLocalConfig(id: Long): Script?

    /**
     * 更新配置文件。
     */
    fun updateConfig(id: Long, script: Script)

}