package com.mercan.app.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mercan.app.data.model.ToguMenuList

class Converter {
    @TypeConverter
    fun fromToguMenuList(value: MutableList<ToguMenuList>): String {
        return Gson().toJson(value) // Listeyi JSON'a çevirir
    }

    @TypeConverter
    fun toToguMenuList(value: String): MutableList<ToguMenuList> {
        val type = object : TypeToken<MutableList<ToguMenuList>>() {}.type
        return Gson().fromJson(value, type) // JSON'u listeye çevirir
    }
}