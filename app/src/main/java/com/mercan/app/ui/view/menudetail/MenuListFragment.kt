package com.mercan.app.ui.view.menudetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mercan.app.databinding.FragmentMenuListBinding
import com.mercan.app.ui.adapter.menulist.MenuListAdapter
import com.mercan.app.ui.viewmodel.MenuListViewModel
import com.mercan.app.util.getDayOfWeek

class MenuListFragment : Fragment() {
    private var _binding: FragmentMenuListBinding? = null
    private val binding get() = _binding!!

    private var position: Int = 0

    private val menuListViewModel: MenuListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt("position")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMenuData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeMenuData() {
        menuListViewModel.menuData.observe(viewLifecycleOwner) {
            if (it.dailyToguMenuLists.isEmpty()) return@observe

            val menu = it.dailyToguMenuLists[position]
            binding.recyclerView.adapter = MenuListAdapter(menu, isActive())
        }
    }

    private fun isActive(): Boolean {
        return position == getDayOfWeek()
    }
}