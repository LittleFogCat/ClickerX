package top.littlefogcat.clickerx.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import top.littlefogcat.clickerx.home.home.HomeFragment
import top.littlefogcat.clickerx.home.localscripts.LocalScriptsFragment
import top.littlefogcat.clickerx.home.me.MeFragment
import top.littlefogcat.clickerx.home.message.MessageFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

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
