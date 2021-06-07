package top.littlefogcat.clickerx.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 重写了[setContentView]，以进行数据绑定。
 *
 * 重写[onDataBound]进行绑定之后的操作，通过成员变量[binding]来操作数据绑定。
 *
 * 实现类需要实现[layoutID]，但在[onCreate]中就不要调用[setContentView]了。
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *        super.onCreate(savedInstanceState)
 *        // do something
 *     }
 *
 *     override fun onDataBound(binding: ConfigDetailActBinding) {
 *        // do something
 *     }
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
abstract class DataBindingActivity<T : ViewDataBinding> : BaseActivity() {
    abstract val layoutID: Int
    protected lateinit var binding: T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutID)
        onDataBound(binding)
    }

    open fun onDataBound(binding: T) {}
}