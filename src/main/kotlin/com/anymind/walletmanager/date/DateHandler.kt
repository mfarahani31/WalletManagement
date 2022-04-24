package com.anymind.walletmanager.date

import org.springframework.stereotype.Component
import java.util.*

@Component
class DateHandler {
    fun diffInHour(endDate: Date, startDate: Date): Int {
        val secs: Long = (endDate.time - startDate.time) / 1000
        return (secs / 3600).toInt()
    }

    fun addHourToTimestamp(date: Date, hours: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.MINUTE, 0);
        calendar.add(Calendar.HOUR_OF_DAY, hours)
        calendar.timeZone =  TimeZone.getTimeZone("UTC");
        return calendar.time
    }
}