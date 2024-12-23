package com.mercan.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.app.data.repository.ToguMenuRepository
import com.mercan.app.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val toguMenuRepository: ToguMenuRepository
) : ViewModel() {
    private val _menuState = MutableStateFlow<UiState>(UiState.Loading)
    val menuState: StateFlow<UiState> get() = _menuState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val data = toguMenuRepository.getData()
                _menuState.value = UiState.Success(data)
            } catch (e: Exception) {
                _menuState.value = UiState.Error("Veriler alınırken bir hata oluştu ${e.message}")
            }
        }
    }
}