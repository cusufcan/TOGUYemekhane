package com.mercan.app.data.local

import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.WeekData
import javax.inject.Inject

class MenuLocalSource @Inject constructor(
    private val menuDao: MenuDao,
) {
    suspend fun getWeekData(): WeekData? {
        return menuDao.getWeekData()
    }

    suspend fun saveWeekData(weekData: WeekData) {
        menuDao.saveWeekData(weekData)
    }

    suspend fun getData(): MenuData? {
        return menuDao.getData()
    }

    suspend fun saveData(menuData: MenuData) {
        menuDao.saveData(menuData)
    }
}