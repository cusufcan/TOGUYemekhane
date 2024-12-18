package com.mercan.app.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mercan.app.databinding.FragmentHomeBinding
import com.mercan.app.ui.adapter.ViewPagerAdapter
import com.mercan.app.utils.getDayName
import com.mercan.app.utils.getDayOfWeek

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        bindingTablayoutWithViewPager()
    }

    private fun bindViews() {
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    private fun bindingTablayoutWithViewPager() {

        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = getDayOfWeek()

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getDayName(position)
        }.attach()
    }
}