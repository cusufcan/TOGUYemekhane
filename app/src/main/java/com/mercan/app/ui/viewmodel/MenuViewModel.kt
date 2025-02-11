package com.mercan.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.app.data.repository.MenuRepository
import com.mercan.app.ui.state.UIMenuState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository,
) : ViewModel() {
    private val _menuState = MutableStateFlow<UIMenuState>(UIMenuState.Loading)
    val menuState: StateFlow<UIMenuState> get() = _menuState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    menuRepository.getData()
                }
                _menuState.value = UIMenuState.Success(data)
            } catch (e: Exception) {
                _menuState.value =
                    UIMenuState.Error("Veriler alınırken bir hata oluştu ${e.message}")
            }
        }
    }
}