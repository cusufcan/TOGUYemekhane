package com.mercan.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.app.data.repository.MenuRepository
import com.mercan.app.ui.state.UIMenuState
import com.mercan.app.ui.state.UIWeekState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {
    private val _menuState = MutableStateFlow<UIMenuState>(UIMenuState.Loading)
    val menuState: StateFlow<UIMenuState> get() = _menuState

    private val _weekState = MutableStateFlow<UIWeekState>(UIWeekState.Loading)
    val weekState: StateFlow<UIWeekState> get() = _weekState

    init {
        fetchData()
        fetchWeekData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val data = menuRepository.getData()
                _menuState.value = UIMenuState.Success(data)
            } catch (e: Exception) {
                _menuState.value =
                    UIMenuState.Error("Veriler alınırken bir hata oluştu ${e.message}")
            }
        }
    }

    private fun fetchWeekData() {
        viewModelScope.launch {
            try {
                val weekData = menuRepository.getWeekData()
                _weekState.value = UIWeekState.Success(weekData)
            } catch (e: Exception) {
                _weekState.value =
                    UIWeekState.Error("Veriler alınırken bir hata oluştu ${e.message}")
            }
        }
    }
}