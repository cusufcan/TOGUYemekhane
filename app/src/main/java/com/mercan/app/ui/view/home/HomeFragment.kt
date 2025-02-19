package com.mercan.app.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mercan.app.R
import com.mercan.app.databinding.FragmentHomeBinding
import com.mercan.app.ui.adapter.viewpager.ViewPagerAdapter
import com.mercan.app.ui.state.UIMenuState
import com.mercan.app.ui.viewmodel.MenuListViewModel
import com.mercan.app.ui.viewmodel.MenuViewModel
import com.mercan.app.util.Day
import com.mercan.app.util.getDayOfWeek
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val menuViewModel: MenuViewModel by viewModels()
    private val menuListViewModel: MenuListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        bindingTablayoutWithViewPager()
        observeScrapedData(view)
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
            tab.text = Day.entries[position].toString()
        }.attach()
    }

    private fun observeScrapedData(view: View) {
        lifecycleScope.launch {
            menuViewModel.menuState.collect { state ->
                when (state) {
                    is UIMenuState.Loading -> dataLoadingLogic()
                    is UIMenuState.Success -> dataSuccessLogic(state)
                    is UIMenuState.Error -> dataErrorLogic(view, state)
                }
            }
        }
    }

    private fun dataLoadingLogic() {
        binding.progressBar.visibility = View.VISIBLE
        binding.viewPager.visibility = View.INVISIBLE
        binding.tvWeek.text = getString(R.string.loading)
    }

    private fun dataSuccessLogic(state: UIMenuState.Success) {
        binding.progressBar.visibility = View.INVISIBLE
        binding.viewPager.visibility = View.VISIBLE

        binding.tvWeek.text = if (state.menuData.weekData != null) {
            val startDate = state.menuData.weekData.startDate
            val endDate = state.menuData.weekData.endDate
            String.format("%s - %s", startDate, endDate)
        } else {
            getString(R.string.data_null)
        }

        menuListViewModel.menuData.postValue(state.menuData)
    }

    private fun dataErrorLogic(view: View, state: UIMenuState.Error) {
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvWeek.text = state.message
        Snackbar.make(
            view, state.message, Snackbar.LENGTH_SHORT
        ).show()
    }
}