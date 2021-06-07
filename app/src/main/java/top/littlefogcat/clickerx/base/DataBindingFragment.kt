package top.littlefogcat.clickerx.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import top.littlefogcat.clickerx.R

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:17
 * @Email：littlefogcat@foxmail.com
 */
abstract class DataBindingFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var binding: T
    protected abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
            .also {
                binding = it
                binding.lifecycleOwner = this
                onDataBinding(it)
            }
            .root

    /**
     * 当[binding]绑定成功，调用这个回调。
     * 在这里传递绑定中需要的数据，例如初始化ViewModel。可以通过[binding]获取布局文件中的控件。
     *
     * 例如：
     * ```
     * override fun onDataBinding(binding: SampleBinding) {
     *      binding.viewModel = ViewModel();
     *      binding.button.setOnClickListener{ exit() }
     * }
     * ```
     */
    abstract fun onDataBinding(binding: T)

}