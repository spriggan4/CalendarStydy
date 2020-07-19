package com.jsc.calendarstydy

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    val CALENDAR_HEADER_FORMAT = "yyyy-MM-dd HH:mm:ss"
    val YEAR_FORMAT = "yyyy"
    val MONTH_FORMAT = "MM"
    val DAY_FORMAT = "d"
    val HOUR_FORMAT = "HH"
    val MIN_FORMAT = "mm"
    val SEC_FORMAT = "ss"

    fun getDate(date: Long, pattern: String?): String? {
        return try {
            val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
            val d = Date(date)
            formatter.format(d).toUpperCase()
        } catch (e: Exception) {
            " "
        }
    }
}