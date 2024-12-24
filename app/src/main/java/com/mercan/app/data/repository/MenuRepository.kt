package com.mercan.app.data.repository

import android.util.Log
import com.mercan.app.data.local.MenuLocalSource
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.WeekData
import com.mercan.app.data.remote.MenuRemoteSource
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val menuRemoteSource: MenuRemoteSource,
    private val menuLocalSource: MenuLocalSource,
) {
    suspend fun getData(): MenuData {
        val remoteWeekData = menuRemoteSource.getWeekData()
        val localWeekData = menuLocalSource.getWeekData()
// TODO: Silinecek
        Log.i("MercanLogger", remoteWeekData.toString())
        Log.i("MercanLogger", localWeekData.toString())

        if (remoteWeekData != null) {
            Log.i("MercanLogger", remoteWeekData.startDate.plus(remoteWeekData.endDate))
            if (localWeekData == null || remoteWeekData.startDate != localWeekData.startDate) {
                val webData = menuRemoteSource.getData()
                menuLocalSource.saveData(webData)
                menuLocalSource.saveWeekData(remoteWeekData)
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