package com.mercan.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeekData(
    val week: String,
) {
    @PrimaryKey
    var id: Int = 0
}
