package com.mercan.app.data.remote

import com.mercan.app.core.Constants
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.MenuList
import com.mercan.app.data.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class MenuRemoteSource @Inject constructor() {
    suspend fun getWeekData(): WeekData? {
        try {
            val document = withContext(Dispatchers.IO) {
                Jsoup.connect(Constants.BASE_URL).get()
            }

            val weekStartDate = document.select(Constants.WEEK_START_ID)
            val weekEndDate = document.select(Constants.WEEK_END_ID)
            val weekData = WeekData(
                startDate = weekStartDate.text(),
                endDate = weekEndDate.text(),
            )
            return weekData
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun getData(): MenuData {
        val document = withContext(Dispatchers.IO) {
            Jsoup.connect(Constants.BASE_URL).get()
        }

        val data = document.select(".style19").toMutableList()
        val dataSubList = data.subList(5, data.size).map { it.text() }

        val dailyMenus = dataSubList.map { element ->
            val regex = Regex("(?=[A-ZÇŞĞÜİÖ])")
            val parts = element.split(regex).map {
                it.trim()
            }.filter {
                it.isNotBlank()
            }.toMutableList()

            if (parts.size <= 1) return@map MenuList(parts)
            val lastElement = parts.last()
            parts.remove(parts.last())
            val secondLastElement = parts.last()
            parts.remove(parts.last())
            parts.add("$secondLastElement $lastElement")

            MenuList(parts)
        }

        return MenuData(dailyMenus.toMutableList())
    }
}