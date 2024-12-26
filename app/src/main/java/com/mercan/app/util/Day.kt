package com.mercan.app.util

enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;

    override fun toString(): String = when (this) {
        MONDAY -> "Pazartesi"
        TUESDAY -> "Salı"
        WEDNESDAY -> "Çarşamba"
        THURSDAY -> "Perşembe"
        FRIDAY -> "Cuma"
    }
}