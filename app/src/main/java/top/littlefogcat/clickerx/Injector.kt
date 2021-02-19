package top.littlefogcat.clickerx

import top.littlefogcat.clickerx.model.*

/**
 * 通过注入的方式来创建对象。
 * 这样在更换数据源的时候就不需要改动太多代码，只需要在特定的数据源中修改具体实现就可以了。
 * 当切换BuildVariants的时候，就会相应的切换数据源。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-2:22
 * @Email littlefogcat@foxmail.com
 */
object Injector {
    /**
     * 推荐列表的数据源。
     */
    fun provideRecommendDataSource(): RecommendDataSource = RecommendRepository()

    /**
     * 返回本地配置文件的数据源。
     */
    fun provideLocalConfigsDataSource(): LocalConfigsDataSource = LocalConfigsRepository()

    /**
     * 消息列表的数据源。
     */
    fun provideMessageDataSource(): MessageDataSource = MessageRepository()

    /**
     * 用户信息的数据源。
     */
    fun provideUserDataSource(): UserDataSource = UserRepository()
}