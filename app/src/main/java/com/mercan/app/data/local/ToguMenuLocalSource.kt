package com.mercan.app.data.local

import com.mercan.app.data.model.ToguMenuData
import com.mercan.app.data.model.WeekData
import javax.inject.Inject

class ToguMenuLocalSource @Inject constructor(
    private val toguMenuDao: ToguMenuDao,
) {
    suspend fun getWeekData(): WeekData {
        return toguMenuDao.getWeekData()
    }

    suspend fun saveWeekData(weekData: WeekData) {
        toguMenuDao.saveWeekData(weekData)
    }

    suspend fun getData(): ToguMenuData {
        return toguMenuDao.getData()
    }

    suspend fun saveData(toguMenuData: ToguMenuData) {
        toguMenuDao.saveData(toguMenuData)
    }
}