package com.mercan.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mercan.app.data.model.ToguMenuData
import com.mercan.app.data.model.WeekData

@Database(
    entities = [
        ToguMenuData::class,
        WeekData::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converter::class)
abstract class ToguMenuDatabase : RoomDatabase() {
    abstract fun getDao(): ToguMenuDao
}