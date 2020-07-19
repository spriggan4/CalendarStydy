package com.jsc.calendarstydy

import java.util.*

class EmptyDay:ViewModel(){

}

class Day : ViewModel() {
    var day: String? = null

    // TODO : day에 달력일값넣기
    fun setCalendar(calendar: Calendar) {
        day = DateUtil().getDate(calendar.getTimeInMillis(), DateUtil().DAY_FORMAT)
    }
}

class CalendarHeader : ViewModel() {
    private var header: String? = null
    var mCurrentTime: Long = 0

    fun getHeader(): String {
        return header as String
    }

    fun setHeader(time: Long) {
        val value: String = DateUtil().getDate(time, DateUtil().CALENDAR_HEADER_FORMAT) as String
        header = value
    }

    fun setHeader(header: String) {
        this.header = header
    }
}

open class ViewModel {

}