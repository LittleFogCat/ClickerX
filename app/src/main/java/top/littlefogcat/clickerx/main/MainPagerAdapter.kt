package top.littlefogcat.clickerx.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import top.littlefogcat.clickerx.configs.ConfigsFragment
import top.littlefogcat.clickerx.home.HomeFragment

class MainPagerAdapter(act: FragmentActivity) : FragmentStateAdapter(act) {
    companion object {
        const val POS_RECOMMEND = 0
        const val POS_LOCAL = 1
        const val POS_MESSAGE = 2
        const val POS_ME = 3
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            POS_RECOMMEND -> HomeFragment.newInstance()
            POS_LOCAL -> ConfigsFragment.newInstance()
            POS_MESSAGE -> HomeFragment.newInstance()
            POS_ME -> ConfigsFragment.newInstance()
            else -> throw IllegalArgumentException("position invalid: $position")
        }
    }

}