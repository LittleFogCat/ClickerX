package top.littlefogcat.clickerx.home

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.databinding.HomeFragBinding
import kotlin.math.abs

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:17
 * @Email：littlefogcat@foxmail.com
 */
class HomeFragment private constructor() : BaseFragment<HomeFragBinding>() {
    override val layoutId: Int = R.layout.home_frag

    override fun onCreateViewModel() {
        binding.viewModel = HomeViewModel()
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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return touchEventHandler.handle(event)
    }

    private val touchEventHandler = object {
        private val UNDEFINE = 0
        private val VERTICAL = 1
        private val HORIZONTAL = 2
        private var downX = 0
        private var downY = 0
        private var lastX = 0
        private var lastY = 0
        private var direction = UNDEFINE

        fun handle(event: MotionEvent): Boolean {
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
                    if (direction == UNDEFINE) {
                        direction = if (abs(dx) > abs(dy)) {
                            HORIZONTAL
                        } else VERTICAL
                    }

                    // 处理头部headerLayout
                    Log.v(TAG, "handle: dy = $dy")
                    if (direction == VERTICAL) {
                        val lp = header.layoutParams as ViewGroup.MarginLayoutParams
                        val height = header.height
                        val minMargin = 0 - height
                        if (dy < 0 && lp.topMargin + height > 0) { // 往上滑
                            Log.d(TAG, "handle: up")
                            val topMargin = lp.topMargin + dy
                            lp.topMargin = if (topMargin < minMargin) minMargin else topMargin
                            header.layoutParams = lp
                            return true
                        } else if (dy > 0 && lp.topMargin < 0) { // 往下滑
                            Log.d(TAG, "handle: down")
                            val topMargin = lp.topMargin + dy
                            lp.topMargin = if (topMargin > 0) 0 else topMargin
                            header.layoutParams = lp
                            return true
                        }
                    }
                    lastX = x
                    lastY = y
                }
                MotionEvent.ACTION_UP -> {
                    if (header.top < 0 && header.bottom > 0) {
                        if (header.top + header.bottom > 0) {
                            // 大部分显示，则滑出
                            val anim = TranslateAnimation(header.x, header.x, header.y, 0f)
                            anim.duration = 300
                            anim.fillAfter = true
                            header.startAnimation(anim)
                        } else {
                            // 大部分不显示
                            val anim = TranslateAnimation(header.x, header.x, header.y, 0f)
                            anim.duration = 300
                            anim.fillAfter = true
                            header.startAnimation(anim)
                        }
                    }
                }
                else -> {

                }
            }
            return false
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
