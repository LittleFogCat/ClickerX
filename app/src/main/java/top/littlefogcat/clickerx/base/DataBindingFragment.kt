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

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        DataBindingUtil.inflate<T>(
            inflater, layoutId, container, false
        ).also { binding = it }.root
}