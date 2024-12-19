package com.mercan.app.util

import com.mercan.app.data.model.ToguMenuData

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: ToguMenuData) : UiState()
    data class Error(val message: String) : UiState()
}