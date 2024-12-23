package com.mercan.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeekData(
    val startDate: String,
    val endDate: String,
) {
    @PrimaryKey
    var id: Int = 0
}
