package top.littlefogcat.clinj

/**
 * 根据`product flavor`对变量进行注入。
 *
 * @see [InjectSrc]
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Inject(val name: String)