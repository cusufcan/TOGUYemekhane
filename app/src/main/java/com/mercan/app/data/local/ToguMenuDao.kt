package com.mercan.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mercan.app.data.model.ToguMenuData
import com.mercan.app.data.model.WeekData

@Dao
interface ToguMenuDao {
    @Query("SELECT * FROM weekdata")
    suspend fun getWeekData(): WeekData

    @Insert
    suspend fun saveWeekData(weekData: WeekData)

    @Query("SELECT * FROM togumenudata")
    suspend fun getData(): ToguMenuData

    @Insert
    suspend fun saveData(toguMenuData: ToguMenuData)
}