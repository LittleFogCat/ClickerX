package top.littlefogcat.clickerx

import top.littlefogcat.clickerx.model.*
import top.littlefogcat.clickerx.utils.ImageLoader
import top.littlefogcat.clickerx.utils.ImageLoaderImpl

/**
 * 根据不同的变体提供不同的对象。
 *
 * 这样在更换数据源的时候就不需要改动太多代码，只需要在特定的数据源中修改具体实现就可以了。
 * 当切换BuildVariants的时候，就会相应的切换数据源。
 *
 * @Author littlefogcat
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
    fun provideConfigsDataSource(): ScrpitsDataSource = ScriptsRepository

    /**
     * 消息列表的数据源。
     */
    fun provideMessageDataSource(): MessageDataSource = MessageRepository()

    /**
     * 用户信息的数据源。
     */
    fun provideUserDataSource(): UserDataSource = UserRepository()

    /**
     * 提供[ImageLoader]的实现类
     */
    fun provideImageLoader(): ImageLoader = ImageLoaderImpl
}