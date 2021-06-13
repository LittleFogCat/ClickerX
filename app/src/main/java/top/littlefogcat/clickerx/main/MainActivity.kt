package top.littlefogcat.clickerx.main

import android.animation.ValueAnimator
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.common.base.DataBindingActivity
import top.littlefogcat.clickerx.core.Core
import top.littlefogcat.clickerx.databinding.MainActBinding
import top.littlefogcat.clickerx.home.HomeFragment
import top.littlefogcat.clickerx.localscripts.LocalScriptsFragment
import top.littlefogcat.clickerx.me.MeFragment
import top.littlefogcat.clickerx.message.MessageFragment
import top.littlefogcat.clickerx.common.utils.loadDrawable
import kotlin.math.abs

class MainActivity : DataBindingActivity<MainActBinding>() {
    override val layoutID: Int = R.layout.main_act

    override fun onDataBound(binding: MainActBinding) {
        initViewPagerAndTab()
        Core.init(this) // 初始化Core
    }

    /**
     * 初始化ViewPager和TabLayout
     */
    private fun initViewPagerAndTab() {
        val viewPager = binding.viewPager

        viewPager.adapter = object : FragmentStateAdapter(this) {
            private val fragments = arrayOf(
                HomeFragment.newInstance(),
                MessageFragment.newInstance(),
                LocalScriptsFragment.newInstance(),
                MeFragment.newInstance()
            )

            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment {
                if (position >= fragments.size) throw IllegalArgumentException("position invalid: $position")
                return fragments[position]
            }

        }
        // 当ViewPager页面发生变化时，对应的调整TabLayout的选择状态。
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.getTabAt(position)?.select()
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                binding.tabLayout.setScrollPosition(position, positionOffset, false)
            }
        })

        val tabLayout = binding.tabLayout
        tabLayout.setSelectedTabIndicator(null)
        // 当TabLayout选择状态发生变化时，调整对应Tab的图标。
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            val normalIcons = arrayOf(
                R.drawable.ic_tab_home, R.drawable.ic_tab_message,
                R.drawable.ic_tab_star, R.drawable.ic_tab_me
            ).map { loadDrawable(it) }
            val selectedIcons = arrayOf(
                R.drawable.ic_tab_home_dark, R.drawable.ic_tab_message_dark,
                R.drawable.ic_tab_star_dark, R.drawable.ic_tab_me_dark
            ).map { loadDrawable(it) }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon = normalIcons[tab.position]
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val pos = tab.position
                Log.d(TAG, "onTabSelected: newTab = $pos")
                tab.icon = selectedIcons[pos]
                if (viewPager.currentItem != tab.position)
                    viewPager.currentItem = tab.position
            }
        })
    }

    // --------------------------------------------------------------------------
    // ------------------ 处理事件分发以及TabLayout的显示/隐藏 ---------------------
    // --------------------------------------------------------------------------
    private var x = 0
    private var y = 0
    private var animRunning = false
    private var tabShowing = true
    private val tabHideAnim by lazy { createTabAnim(false) }
    private val tabShowAnim by lazy { createTabAnim(true) }
    private var shownMargin: Int = -1
    private var hiddenMargin: Int = 1

    /**
     * 创建TabLayout的动画，如果[show]为true，返回显示的动画，反之返回隐藏的动画。
     */
    private fun createTabAnim(show: Boolean): ValueAnimator {
        val tabLayout = binding.tabLayout
        val lp = tabLayout.layoutParams as ViewGroup.MarginLayoutParams
        val h = tabLayout.height

        if (shownMargin < 0) shownMargin = lp.bottomMargin
        if (hiddenMargin > 0) hiddenMargin = -h - lp.topMargin
        val before = if (show) hiddenMargin else shownMargin
        val after = if (show) shownMargin else hiddenMargin

        return ValueAnimator.ofInt(before, after).apply {
            duration = 300
            addUpdateListener {
                val value = it.animatedValue as Int
                lp.bottomMargin = value
                tabLayout.layoutParams = lp
            }
            addListener(
                onEnd = {
                    animRunning = false
                    tabShowing = show
                },
                onStart = { animRunning = true },
                onCancel = { animRunning = false },
                onRepeat = { animRunning = true }
            )
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val viewPager = binding.viewPager
        if (viewPager.scrollState != ViewPager2.SCROLL_STATE_IDLE) {
            return super.dispatchTouchEvent(event)
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x = event.x.toInt()
                y = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx: Int = (event.x - x).toInt()
                val dy: Int = (event.y - y).toInt()
                if (viewPager.isUserInputEnabled) {
                    if (abs(dy) > abs(dx)) {
                        // 竖直滚动，禁用viewpager
                        viewPager.isUserInputEnabled = false
                    }
                } else if (!animRunning) {
                    if (dy < 0 && tabShowing) { // 竖直向下滚动，隐藏tab
                        tabHideAnim.start()
                    } else if (dy > 0 && !tabShowing) { // 向上滚动，显示tab
                        tabShowAnim.start()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                viewPager.isUserInputEnabled = true
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onStop() {
        super.onStop()
//        Handler().postDelayed({
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                Log.d(TAG, "SendNotification")
//                val n = Notification.Builder(applicationContext, packageName)
//                    .setContentText("It's a notification")
//                    .setContentTitle("Title")
//                    .setSmallIcon(R.drawable.fake_head)
//                    .build()
//                val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//                nm.createNotificationChannel(NotificationChannel(packageName, "title", NotificationManager.IMPORTANCE_HIGH))
//                nm.notify(1111, n)
//            }
//        }, 3000)
    }

}