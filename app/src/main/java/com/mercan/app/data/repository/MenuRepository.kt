package com.mercan.app.data.repository

import com.mercan.app.data.local.MenuLocalSource
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.remote.MenuFirestoreSource
import com.mercan.app.data.remote.MenuRemoteSource
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val menuRemoteSource: MenuRemoteSource,
    private val menuLocalSource: MenuLocalSource,
    private val menuFirestoreSource: MenuFirestoreSource,
) {
    suspend fun getData(): MenuData {
        val remoteWeekData = menuRemoteSource.getWeekData()
        val localWeekData = menuLocalSource.getWeekData()

        if (remoteWeekData != null) {
            if (localWeekData == null || remoteWeekData.startDate != localWeekData.startDate) {
                val webData = menuRemoteSource.getData()
                if (webData.dailyMenuLists?.all { it.menuList?.size == 1 } == true) {
                    val localData = menuLocalSource.getData() ?: return MenuData()
                    return localData
                }
                menuLocalSource.saveData(webData)
                menuLocalSource.saveWeekData(remoteWeekData)
                menuFirestoreSource.saveData(remoteWeekData, webData)
                return webData
            } else {
                val localData = menuLocalSource.getData() ?: return MenuData()
                return localData
            }
        } else {
            if (localWeekData == null) return MenuData()
            val localData = menuLocalSource.getData() ?: return MenuData()
            return localData
        }
    }
}