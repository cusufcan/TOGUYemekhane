package com.mercan.app.utils

import java.util.Calendar

fun getDayName(day: Int): String {
    return when (day) {
        0 -> "Pazartesi"
        1 -> "Salı"
        2 -> "Çarşamba"
        3 -> "Perşembe"
        4 -> "Cuma"
        else -> "Geçersiz Gün"
    }
}

fun getDayOfWeek(): Int {
    val calendar = Calendar.getInstance()
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) return 0
    return dayOfWeek - 2
}