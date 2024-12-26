package com.mercan.app.data.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.WeekData
import com.mercan.app.util.Day
import com.mercan.app.util.reformatWeekDateForFirebase
import javax.inject.Inject

class MenuFirestoreSource @Inject constructor() {
    private val firestore = Firebase.firestore

    fun saveData(weekData: WeekData, data: MenuData) {
        val collection = "menus"
        val document = weekData.startDate.reformatWeekDateForFirebase()

        firestore.collection(collection).document(document).set(
            data.dailyMenuLists.mapIndexed { index, dailyMenu ->
                "$index ${Day.entries[index]}" to dailyMenu.menuList
            }.toMap()
        )
    }
}