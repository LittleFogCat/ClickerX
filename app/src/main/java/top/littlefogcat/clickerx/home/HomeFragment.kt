package top.littlefogcat.clickerx.home

import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.databinding.HomeFragBinding

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:17
 * @Email：littlefogcat@foxmail.com
 */
class HomeFragment(override val layoutId: Int) : BaseFragment<HomeFragBinding>() {
    companion object {
        fun newInstance() = HomeFragment(R.layout.home_frag)
    }
}