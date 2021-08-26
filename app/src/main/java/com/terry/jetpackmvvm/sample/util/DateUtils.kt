package com.terry.jetpackmvvm.sample.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val inputFormat = "yyyy/MM/dd HH:mm:ss"

    /**
     * Example:
     * DateUtils.isCurrentTimeInRange("2021/01/01 00:00:00", "2021/02/1 00:00:00")
     */
    fun isCurrentTimeInRange(from: String, to: String): Boolean {
        val current = Date()
        val fromDate = parseDate(from)
        val toDate = parseDate(to)

        if (current.before(toDate) && current.after(fromDate)) {
            return true
        }

        return false
    }

    /**
     * Example:
     * DateUtils.isCurrentTimeInRange("2021/02/11 00:00:00", 2)
     */
    fun isCurrentInFromToDays(from: String, days: Int): Boolean {
        try {
            val currentDate = Date()
            val expireDate = addDays(from, days)
            return expireDate.after(currentDate)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    fun isCurrentInFromToDays(from: Calendar, days: Int): Boolean {
        try {
            val currentDate = Date()
            val expireDate = addDays(from, days)
            return expireDate.after(currentDate)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    /**
     * Example:
     * DateUtils.isCurrentTimeInRange("2021/01/01 00:00:00", "2021/01/1 00:00:00")
     */
    fun isSameDay(a: String, b: String): Boolean {
        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()
        c1.time = parseDate(a)
        c2.time = parseDate(b)

        return c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
    }

    /**
     * Example:
     * DateUtils.convertDateString("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd");
     */
    fun convertDateString(date: String, inputPattern: String, outputPattern: String): String {
        return try {
            val outFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
            outFormat.format(parseDate(date, inputPattern))
        } catch (ex: Exception) {
            ex.printStackTrace()
            ""
        }
    }

    fun convertTimestampToDateString(a: Long, outputPattern: String): String {
        return try {
            val outFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
            outFormat.format(Date(a * 1000))
        } catch (ex: Exception) {
            ex.printStackTrace()
            ""
        }
    }

    fun addDays(from: String, days: Int): Date {
        val fromDate = parseDate(from)
        val c = Calendar.getInstance()
        c.time = fromDate
        c.add(Calendar.DATE, days)
        return c.time
    }

    fun addDays(from: Calendar, days: Int): Date {
        var c =  from.clone() as Calendar
        c.add(Calendar.DATE, days)
        return c.time
    }

    fun clearTime(calendar: Calendar) {
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
    }

    fun parseDate(date: String, pattern: String): Date {
        return try {
            val format = SimpleDateFormat(pattern, Locale.getDefault())
            format.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date(0)
        }

    }

    fun parseDate(date: String): Date {
        return try {
            val inputParser = SimpleDateFormat(inputFormat, Locale.getDefault())
            inputParser.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            Date(0)
        }
    }
}