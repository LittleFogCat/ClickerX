package top.littlefogcat.clickerx.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_act.*
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseActivity
import top.littlefogcat.clickerx.configs.ConfigsFragment
import top.littlefogcat.clickerx.home.HomeFragment
import top.littlefogcat.clickerx.message.MessageFragment
import top.littlefogcat.clickerx.utils.loadDrawable

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        initViewPagerAndTab()
        initFragments()
    }

    private fun initViewPagerAndTab() {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment.newInstance()
                    1 -> ConfigsFragment.newInstance()
                    2 -> MessageFragment.newInstance()
                    3 -> ConfigsFragment.newInstance()
                    else -> throw IllegalArgumentException("position invalid: $position")
                }
            }

        }
//            MainPagerAdapter(this)
        // 当ViewPager页面发生变化时，对应的调整TabLayout的选择状态。
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                tabLayout.setScrollPosition(position, positionOffset, false)
            }
        })


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
                if (viewPager.currentItem != tab.position) viewPager.currentItem = tab.position
            }
        })
    }

    private fun initFragments() {
//        loadFragment(R.id.contentFrame, HomeFragment.newInstance())
    }

}