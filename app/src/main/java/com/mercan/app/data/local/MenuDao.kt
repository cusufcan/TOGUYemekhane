package com.mercan.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.WeekData

@Dao
interface MenuDao {
    @Query("SELECT * FROM weekdata")
    suspend fun getWeekData(): WeekData?

    @Insert
    suspend fun saveWeekData(weekData: WeekData)

    @Query("SELECT * FROM menudata")
    suspend fun getData(): MenuData?

    @Insert
    suspend fun saveData(menuData: MenuData)
}