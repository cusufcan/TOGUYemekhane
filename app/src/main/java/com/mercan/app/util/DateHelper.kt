package com.mercan.app.util

import java.util.Calendar

fun getDayOfWeek(): Int {
    val calendar = Calendar.getInstance()
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) return 0
    return dayOfWeek - 2
}

fun String.reformatWeekDateForFirebase(): String {
    val splitted = this.split(".")
    return String.format("%s.%s.%s", splitted[2], splitted[1], splitted[0])
}