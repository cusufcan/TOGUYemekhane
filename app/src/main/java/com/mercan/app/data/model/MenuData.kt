package com.mercan.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuData(
    val dailyMenuLists: MutableList<MenuList>?,
) {
    @PrimaryKey
    var id: Int = 0
}