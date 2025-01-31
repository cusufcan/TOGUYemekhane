package com.mercan.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mercan.app.data.model.MenuData
import com.mercan.app.data.model.WeekData

@Database(
    entities = [
        MenuData::class,
        WeekData::class,
    ],
    version = 3,
    exportSchema = false,
)
@TypeConverters(Converter::class)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun getDao(): MenuDao
}