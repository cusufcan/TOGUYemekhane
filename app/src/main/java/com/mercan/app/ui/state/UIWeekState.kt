package com.mercan.app.ui.state

import com.mercan.app.data.model.WeekData

sealed class UIWeekState {
    data object Loading : UIWeekState()
    data class Success(val weekData: WeekData) : UIWeekState()
    data class Error(val message: String) : UIWeekState()
}