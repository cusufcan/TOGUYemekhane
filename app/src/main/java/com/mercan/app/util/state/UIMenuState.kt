package com.mercan.app.util.state

import com.mercan.app.data.model.MenuData

sealed class UIMenuState {
    data object Loading : UIMenuState()
    data class Success(val menuData: MenuData) : UIMenuState()
    data class Error(val message: String) : UIMenuState()
}