package top.littlefogcat.clickerx.base

import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.MainScope

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:37
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseFragment<T : ViewDataBinding> : DataBindingFragment<T>() {
    protected val coroutine =  MainScope()
}