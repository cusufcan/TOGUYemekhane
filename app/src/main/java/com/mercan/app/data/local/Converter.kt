package com.mercan.app.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mercan.app.data.model.MenuList

class Converter {
    @TypeConverter
    fun fromToguMenuList(value: MutableList<MenuList>): String {
        return Gson().toJson(value) // Listeyi JSON'a çevirir
    }

    @TypeConverter
    fun toToguMenuList(value: String): MutableList<MenuList> {
        val type = object : TypeToken<MutableList<MenuList>>() {}.type
        return Gson().fromJson(value, type) // JSON'u listeye çevirir
    }
}