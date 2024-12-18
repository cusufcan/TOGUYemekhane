package com.mercan.app.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mercan.app.ui.view.menudetail.MenuListFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = MenuListFragment()
        fragment.arguments = Bundle().apply {
            putInt("object", position)
        }
        return fragment
    }
}