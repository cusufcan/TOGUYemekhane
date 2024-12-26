package com.mercan.app.data.repository

import com.mercan.app.data.local.MenuLocalSource
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.WeekData
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
                menuLocalSource.saveData(webData)
                menuLocalSource.saveWeekData(remoteWeekData)
                menuFirestoreSource.saveData(remoteWeekData, webData)
                return webData
            } else {
                val localData = menuLocalSource.getData() ?: return MenuData(mutableListOf())
                return localData
            }
        } else {
            if (localWeekData == null) return MenuData(mutableListOf())
            val localData = menuLocalSource.getData() ?: return MenuData(mutableListOf())
            return localData
        }
    }

    suspend fun getWeekData(): WeekData {
        val remoteWeekData = menuRemoteSource.getWeekData()
        val localWeekData = menuLocalSource.getWeekData()

        if (remoteWeekData != null) {
            return if (localWeekData == null || remoteWeekData.startDate != localWeekData.startDate) {
                remoteWeekData
            } else {
                localWeekData
            }
        } else {
            if (localWeekData == null) throw Exception("İnternet yok")
            return localWeekData
        }
    }
}