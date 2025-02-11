package com.mercan.app.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mercan.app.data.model.MenuList
import com.mercan.app.data.model.WeekData

class Converter {
    @TypeConverter
    fun fromToguMenuList(value: MutableList<MenuList>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toToguMenuList(value: String): MutableList<MenuList> {
        val type = object : TypeToken<MutableList<MenuList>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWeekData(value: WeekData): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWeekData(value: String): WeekData {
        val type = object : TypeToken<WeekData>() {}.type
        return Gson().fromJson(value, type)
    }
}