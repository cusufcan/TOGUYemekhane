package com.mercan.app.data.remote

import com.mercan.app.data.model.ToguMenuData
import com.mercan.app.data.model.ToguMenuList
import com.mercan.app.data.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class ToguMenuRemoteSource @Inject constructor() {
    suspend fun getWeekData(): WeekData {
        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("https://sosyaltesisler.gop.edu.tr/yemekhane_menu.aspx").get()
        }

        val data = document.select("#ContentPlaceHolder1_haftaBasi").toMutableList().first()
        val weekData = WeekData(data.text())
        return weekData
    }

    suspend fun getData(): ToguMenuData {
        // Verileri internetten çek
        val document = withContext(Dispatchers.IO) {
            Jsoup.connect("https://sosyaltesisler.gop.edu.tr/yemekhane_menu.aspx").get()
        }

        // Verileri küçült
        val data = document.select(".style19").toMutableList()
        val dataSubList = data.subList(5, data.size)

        // Her bir öğeyi işleyip listeye geçir
        val dailyMenus = dataSubList.map { element ->
            val regex = Regex("(?=[A-ZÇŞĞÜİÖ])")
            val parts = element.text().split(regex).map {
                it.trim()
            }.filter {
                it.isNotBlank()
            }.toMutableList()

            // "Toplam Kalori: 1300" kısmını birleştir
            val lastElement = parts.last()
            parts.remove(parts.last())
            val secondLastElement = parts.last()
            parts.remove(parts.last())
            parts.add("$secondLastElement $lastElement")

            ToguMenuList(parts)
        }

        // Sonucu geriye döndür
        return ToguMenuData(dailyMenus.toMutableList())
    }
}