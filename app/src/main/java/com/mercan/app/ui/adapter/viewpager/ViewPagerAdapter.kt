package com.mercan.app.ui.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mercan.app.ui.view.menudetail.MenuListFragment
import com.mercan.app.util.getDayOfWeek

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = MenuListFragment()
        fragment.arguments = Bundle().apply {
            putInt("position", position)
            putBoolean("is_active", position == getDayOfWeek())
        }
        return fragment
    }
}