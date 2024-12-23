package com.mercan.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToguMenuData(
    val dailyToguMenuLists: MutableList<ToguMenuList>,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}