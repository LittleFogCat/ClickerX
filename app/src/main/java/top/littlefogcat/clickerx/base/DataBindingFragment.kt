package top.littlefogcat.clickerx.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

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
                onCreateViewModel()
            }.root

    /**
     * 当[binding]绑定成功，调用这个回调，初始化ViewModel
     *
     * binding.viewModel1 = ViewModel1()
     * binding.viewModel2 = ViewModel2()
     * ....
     */
    abstract fun onCreateViewModel()

}