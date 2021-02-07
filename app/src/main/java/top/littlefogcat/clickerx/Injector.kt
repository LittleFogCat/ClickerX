package top.littlefogcat.clickerx

import top.littlefogcat.clickerx.model.ConfigsDataSource
import top.littlefogcat.clickerx.model.ConfigsDataSourceImpl

/**
 * 通过注入的方式来创建对象。
 * 这样在更换数据源的时候就不需要改动太多代码，只需要在特定的数据源中修改具体实现就可以了。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-2:22
 * @Email littlefogcat@foxmail.com
 */
object Injector {
    /**
     * 返回配置文件的数据源。
     */
    fun provideConfigsDataSource(): ConfigsDataSource = ConfigsDataSourceImpl()
}