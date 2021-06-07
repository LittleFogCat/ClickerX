package top.littlefogcat.clickerx.home

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseViewPagerFragment
import top.littlefogcat.clickerx.databinding.HomeFragBinding
import top.littlefogcat.clickerx.utils.getViewModel
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:17
 * @Email：littlefogcat@foxmail.com
 */
class HomeFragment : BaseViewPagerFragment<HomeFragBinding>() {
    override val layoutId: Int = R.layout.home_frag

    override fun onDataBinding(binding: HomeFragBinding) {
        binding.viewModel = getViewModel(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        binding.searchBox.setOnClickListener {

        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecommendListAdapter()
        }

        binding.viewModel?.loadRecommendList()
        binding.viewModel?.loadRecommendSearch()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return touchEventHandler.dispatch(event)
    }

    private val touchEventHandler = object {
        private val UNDEFINE = 0
        private val VERTICAL = 1

        @Deprecated("no use")
        private val HORIZONTAL = 2
        private var downX = 0
        private var downY = 0
        private var lastX = 0
        private var lastY = 0
        private var scrollMode = UNDEFINE
        private val viewPager: ViewPager2 by lazy {
            var v: ViewParent = binding.root as ViewGroup
            while (v !is ViewPager2) {
                v = v.parent
            }
            v
        }
        private var runningAnim: Animator? = null

        /**
         * 主要拦截上下滑动的时候需要隐藏/显示头部的事件
         */
        fun dispatch(event: MotionEvent): Boolean {
            val header = header()
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    downX = x
                    lastX = x
                    downY = y
                    lastY = y
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = x - lastX
                    val dy = y - lastY
                    lastX = x
                    lastY = y

                    if (viewPager.scrollState != ViewPager2.SCROLL_STATE_IDLE) {
                        // ViewPager正在拖动，交由ViewPager处理
                        return false
                    }
                    if (scrollMode == UNDEFINE && abs(dy) > abs(dx)) {
                        scrollMode = VERTICAL // 竖直滑动
                    }

                    // 一旦进入VERTICAL模式，就不允许左右滑动了
                    if (scrollMode == VERTICAL) { // 正在上下滑动
                        if (viewPager.isUserInputEnabled) { // 禁止ViewPager拖动
                            viewPager.isUserInputEnabled = false
                        }
                        val lp = header.layoutParams as ViewGroup.MarginLayoutParams
                        val height = header.height
                        val margin = lp.topMargin
                        val minMargin = -height
                        val newMargin =
                            if (dy < 0 && lp.topMargin + height > 0) { // 往上滑
                                max(minMargin, lp.topMargin + dy) // 不小于minMargin
                            } else if (dy > 0 && lp.topMargin < 0) { // 往下滑
                                min(lp.topMargin + dy, 0) // 不大于0
                            } else margin
                        if (newMargin != margin) { // 拦截事件，操作header
                            lp.topMargin = newMargin
                            header.layoutParams = lp
                            return true
                        }
                    }
                }
                MotionEvent.ACTION_UP -> {
                    startHeaderAnimation()
                    scrollMode = UNDEFINE
                    viewPager.isUserInputEnabled = true
                }
            }
            return false
        }

        private fun startHeaderAnimation() {
//            Log.d(TAG, "startHeaderAnimation: ")
            if (runningAnim != null && runningAnim!!.isRunning) {
                Log.i(TAG, "startHeaderAnimation: already running")
                return
            }
            val header = header()
            val h = header.height
            val lp = header.layoutParams as ViewGroup.MarginLayoutParams
            val marginTop = lp.topMargin
            val midMargin = -h / 2

            if (marginTop <= -h || marginTop >= 0) return // 已经完全显示或隐藏

            // 在中点之上，隐藏；在中点之下，显示
            runningAnim = ValueAnimator.ofInt(marginTop, if (marginTop < midMargin) -h else 0)
                .apply {
                    duration = 300
//                    interpolator = LinearInterpolator()
                    addUpdateListener {
                        val value = it.animatedValue as Int // 动画进行程度的百分数
                        lp.topMargin = value
                        header.layoutParams = lp
                    }
                    start()
                }
        }

    }

    /**
     * 头部layout
     */
    private fun header() = binding.searchLayout

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume: ")
        binding.viewModel
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause: ")
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
