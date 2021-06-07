package top.littlefogcat.clickerx.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2

/**
 * 在ViewPager2中的Fragment
 *
 * @Author：littlefogcat
 * @Date：2021/2/23-1:11
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseViewPagerFragment<T : ViewDataBinding> : BaseFragment<T>() {
    protected val viewPager: ViewPager2 by lazy {
        val root = binding.root
        var v = root
        while (v !is ViewPager2) {
            v = v.parent as View
        }
        v
    }

    private var x = 0
    private var y = 0

//    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
//        if (viewPager.scrollState != ViewPager2.SCROLL_STATE_IDLE) {
//            return false
//        }
//
//        when (event.action) {
//            MotionEvent.ACTION_DOWN -> {
//                x = event.x.toInt()
//                y = event.y.toInt()
//            }
//            MotionEvent.ACTION_MOVE -> {
//                if (viewPager.isUserInputEnabled) {
//                    val dx: Int = (event.x - x).toInt()
//                    val dy: Int = (event.y - y).toInt()
//                    if (abs(dy) > abs(dx)) {
//                        // 竖直滚动，禁用viewpager
//                        viewPager.isUserInputEnabled = false
//                    }
//                }
//            }
//            MotionEvent.ACTION_UP -> {
//                viewPager.isUserInputEnabled = true
//            }
//        }
//        return false
//    }
}