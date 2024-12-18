package com.mercan.app.ui.view.menudetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mercan.app.data.model.Meal
import com.mercan.app.data.model.MealData
import com.mercan.app.databinding.FragmentMenuListBinding
import com.mercan.app.ui.adapter.menulist.MenuListAdapter
import com.mercan.app.utils.getDayOfWeek
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MenuListFragment : Fragment() {
    private var _binding: FragmentMenuListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mealData: MealData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webScrapingCodes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun webScrapingCodes() {
        mealData = MealData()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val document = Jsoup
                    .connect("https://sosyaltesisler.gop.edu.tr/yemekhane_menu.aspx")
                    .get()

                val data = document.select(".style19").toMutableList()
                val dataSubList = data.subList(5, data.size)

                for (element in dataSubList) {
                    val regex = Regex("(?=[A-ZÇŞĞÜİÖ])")
                    val parts = element.text().split(regex).map {
                        it.trim()
                    }.filter {
                        it.isNotBlank()
                    }.toMutableList()

                    val lastElement = parts.last()
                    parts.remove(parts.last())
                    val secondLastElement = parts.last()
                    parts.remove(parts.last())
                    parts.add("$secondLastElement $lastElement")

                    val meal = Meal(parts)
                    mealData.dailyMeal.add(meal)
                }

                withContext(Dispatchers.Main) {
                    var dayIndex = 0
                    arguments?.takeIf { it.containsKey("object") }?.apply {
                        dayIndex = getInt("object")
                    }

                    val menuListAdapter = MenuListAdapter(
                        mealData.dailyMeal[dayIndex],
                        isActive = getDayOfWeek() == dayIndex,
                    )

                    binding.recyclerView.adapter = menuListAdapter

                    Log.i("MercanLogger", mealData.dailyMeal.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}