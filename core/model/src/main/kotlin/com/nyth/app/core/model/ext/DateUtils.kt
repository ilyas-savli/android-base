package com.nyth.app.core.model.ext

import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun convertMillisToDateString(millis: Long, format: String = "dd.MM.yyyy"): String? {
        return try {
            val date = Date(millis)
            val formattedDate = SimpleDateFormat(format, Locale.getDefault()).format(date)
            return formattedDate
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
    fun String.toDateOrNull(
        inputFormat: String = "dd.MM.yyyy"
    ): Date? {
        return try {
            val formatter = SimpleDateFormat(inputFormat, Locale.getDefault())
            return formatter.parse(this)
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    fun getCurrentDateTime(): String {
        val now = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return sdf.format(now)
    }

    fun getDateUTCFormat(date: Date): String {
        val tz = TimeZone.getTimeZone("UTC")
        val df: DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm'Z'",
            Locale.getDefault()
        )

        df.timeZone = tz
        return df.format(date)
    }
}