package com.mercan.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.app.data.model.ToguMenu
import com.mercan.app.data.model.ToguMenuData
import com.mercan.app.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MenuViewModel : ViewModel() {
    private val _menuState = MutableStateFlow<UiState>(UiState.Loading)
    val menuState: StateFlow<UiState> get() = _menuState

    init {
        fetchToguMenuData()
    }

    private fun fetchToguMenuData() {
        viewModelScope.launch {
            try {
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

                    ToguMenu(parts)
                }

                // Sonucu state'e aktar
                _menuState.value = UiState.Success(ToguMenuData(dailyMenus.toMutableList()))
            } catch (e: Exception) {
                // Hata durumunu state'e aktar
                _menuState.value = UiState.Error("Veriler alınırken bir hata oluştu ${e.message}")
            }
        }
    }
}