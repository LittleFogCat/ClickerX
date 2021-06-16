package top.littlefogcat.clinj

/**
 * 标记一个类是在 product flavor 为 [flavor] 的情况下使用的。
 *
 * 这个注解需要配合[Inject]注解和[Clinj.inject]使用，
 * 用途是根据当前的`product flavor`对变量进行注入。
 *
 *
 * 示例 - 假设现在有"mock"和"prod"两种flavor：
 * ```kotlin
 * @Flavored(name = "user_service", flavor = "mock")
 * object UserRepositoryImplMock: UserRepository {}
 *
 * @Flavored(name = "user_service", flavor = "prod")
 * object UserRepositoryImpl: UserRepository {}
 *
 * class UserViewModel {
 *     @AutoWiredByFlavor("mock")
 *     lateinit var userRepository: UserRepository
 *
 *     init{ FlavorInjector.inject(this) }
 * }
 *
 * ```
 *
 * @param flavor 这个类在哪个flavor下面生效
 * @param name 注入的对象的名称，是唯一标识符
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectSrc(val name: String, val flavor: String)