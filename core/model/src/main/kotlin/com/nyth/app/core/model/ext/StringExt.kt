package com.nyth.app.core.model.ext

import android.text.Html
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyth.app.core.model.local.Screen
import com.nyth.app.core.model.utils.AppConstants.Companion.PHONE_MASK
import com.nyth.app.core.model.utils.AppConstants.Companion.PHONE_MASK_CHAR
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

object StringExt {
    val String.Companion.empty: String
        inline get() = ""
    val String.Companion.space: String
        inline get() = " "
    val String.Companion.requiredSymbol: String
        inline get() = "*"
    val String.Companion.slash: String
        inline get() = "/"
    val String.Companion.newLine: String
        inline get() = "\n"
    val String.Companion.dash: String
        inline get() = "-"
    val String.Companion.pharantesses: String
        inline get() = "(%s)"

    private const val PHONE_REGEX =
        "^(\\+90|0)?\\s*(\\(\\d{3}\\)[\\s-]*\\d{3}[\\s-]*\\d{2}[\\s-]*\\d{2}|\\(\\d{3}\\)[\\s-]*\\d{3}[\\s-]*\\d{4}|\\(\\d{3}\\)[\\s-]*\\d{7}|\\d{3}[\\s-]*\\d{3}[\\s-]*\\d{4}|\\d{3}[\\s-]*\\d{3}[\\s-]*\\d{2}[\\s-]*\\d{2})\$"
    private const val DEFAULT_DATE_FORMAT = "dd.MM.yyyy"

    fun String.toDateFormattedString(
        inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        outputFormat: String = "dd.MM.yyyy",
        locale: Locale = Locale.getDefault()
    ): String? {
        var formatter = SimpleDateFormat(inputFormat, locale) // Set locale for parsing
        try {
            val parsedDate = formatter.parse(this)
            return if (parsedDate != null) {
                SimpleDateFormat(
                    outputFormat,
                    locale
                ).format(parsedDate) // Use locale for formatting
            } else {
                null
            }
        } catch (e: Exception) {
            Timber.i(e)
            return try {
                formatter = SimpleDateFormat(inputFormat.replace(".SSS", ""), locale)
                val parsedDate = formatter.parse(this)
                if (parsedDate != null) {
                    SimpleDateFormat(outputFormat, locale).format(parsedDate)
                } else {
                    null
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }
    }

    private fun String.toSimpleDateFormatter(
        inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    ): SimpleDateFormat? {
        var formatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        try {
            val parsedDate = formatter.parse(this)
            return if (parsedDate != null) {
                formatter
            } else {
                null
            }
        } catch (e: Exception) {
            Timber.i(e)
            return try {
                formatter = SimpleDateFormat(inputFormat.replace(".SSS", ""), Locale.getDefault())
                val parsedDate = formatter.parse(this)
                if (parsedDate != null) {
                    formatter
                } else {
                    null
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }
    }

    fun String.toReadableDate(): String {
        return this.toDateFormattedString(outputFormat = "d MMMM yyyy")
            ?: String.empty
    }

    fun String.toFormatDayMonthDate(): String {
        val date: Date? = this.toSimpleDateFormatter()?.parse(this)
        val today = Calendar.getInstance().time

        return if (date != null && isSameDay(date, today)) {
            "Bugün"
        } else {
            this.toDateFormattedString(
                outputFormat = "dd MMMM",
                locale = Locale("tr")
            )
        } ?: String.empty
    }

    fun String.toFormatMonthYearDate(): String {
        return this.toDateFormattedString(outputFormat = "MMMM yyyy")
            ?: String.empty
    }

    fun String.toFormatDayMonthDayNameDate(): String {
        val date: Date? = this.toSimpleDateFormatter()?.parse(this)
        val today = Calendar.getInstance().time

        return if (date != null && isSameDay(date, today)) {
            "Bugün"
        } else {
            this.toDateFormattedString(outputFormat = "dd MMMM EEEE")
                ?: String.empty
        }
    }

    fun String.toFormatDayNameDate(): String {
        return this.toDateFormattedString(outputFormat = "EEEE")
            ?: String.empty
    }

    fun String.toFormatTimeDate(): String {
        return this.toDateFormattedString(outputFormat = "hh:mm a")
            ?: String.empty
    }

    fun String.toDayAndShortMonth(): Pair<String, String> {
        val dayFormat = this.toDateFormattedString(outputFormat = "dd")
        val monthFormat = this.toDateFormattedString(outputFormat = "MMM")

        val day = dayFormat ?: String.empty
        val month = monthFormat ?: String.empty

        return Pair(day, month)
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val calendar1 = Calendar.getInstance().apply { time = date1 }
        val calendar2 = Calendar.getInstance().apply { time = date2 }
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }

    fun String.toLongDateFormat(): String {
        return this.toDateFormattedString(outputFormat = "dd MMMM yyyy") ?: String.empty
    }

    fun String.calculateTimeFromNow(format: String? = null): String {
        val dateFormat = SimpleDateFormat(format ?: DEFAULT_DATE_FORMAT, Locale.getDefault())
        return try {
            val date = dateFormat.parse(this)
            if (date == null) {
                String.empty
            } else {
                val now = Calendar.getInstance().time
                val differenceInMillis = now.time - date.time

                val differenceInDays = (differenceInMillis / (1000 * 60 * 60 * 24)).toInt()
                val differenceInMonths = (differenceInDays / 30)
                val differenceInYears = (differenceInMonths / 12)
                val remainingMonths = differenceInMonths % 12

                when {
                    differenceInMonths <= 1 -> "Yeni Üye"
                    differenceInYears > 0 -> {
                        if (remainingMonths > 0) {
                            "$differenceInYears yıl $remainingMonths ay"
                        } else {
                            "$differenceInYears yıl"
                        }
                    }

                    else -> "$differenceInMonths aydır üye"
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            "Error parsing date"
        }
    }


    fun String.stripHtmlTags(): String =
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()

    fun String.stripHtmlTagsIfNeeded(): String {
        return if (contains("<") && contains(">")) {
            stripHtmlTags()
        } else {
            this
        }
    }

    fun Any?.toStringOrNull(): String? = this?.toString()
    fun String.isPhoneNumber(): Boolean {
        val patternWithPrefix: Pattern = Pattern.compile(PHONE_REGEX)

        return patternWithPrefix.matcher(this).find()
    }

    fun String.phoneMask(): String? {
        val cleanPhoneNumber = this.filter { it.isDigit() }
        val mask = PHONE_MASK
        val maskNumber = PHONE_MASK_CHAR
        val result = buildString {
            var index = 0
            for (ch in mask) {
                if (index >= cleanPhoneNumber.length) break

                if (ch == maskNumber) {
                    append(cleanPhoneNumber[index])
                    index++
                } else if (ch.isDigit() && ch == cleanPhoneNumber[index]) {
                    append(cleanPhoneNumber[index])
                    index++
                } else {
                    append(ch)
                }
            }
        }
        return result
    }

    fun String.cleanPhoneMask(): String = this.filter { it.isDigit() || it == '+' }

    fun String?.stringRepresentableSince(): String? {
        val dateString = this ?: return null

        val dateFormat = this.toSimpleDateFormatter()

        return try {
            val date = dateFormat?.parse(dateString) ?: return null

            val currentDate = Date()
            val timeDifference =
                TimeUnit.MILLISECONDS.toSeconds(currentDate.time - date.time).toInt()

            val minute = 60
            val hour = 60 * minute
            val day = 24 * hour
            val week = 7 * day
            val month = 30 * day
            val year = 365 * day

            when {
                timeDifference >= year -> {
                    val years = timeDifference / year
                    String.format("%d yıl", years)
                }

                timeDifference >= month -> {
                    val months = timeDifference / month
                    String.format("%d ay", months)
                }

                timeDifference >= week -> {
                    val weeks = timeDifference / week
                    String.format("%d hafta", weeks)
                }

                timeDifference >= day -> {
                    val days = timeDifference / day
                    String.format("%d gün", days)
                }

                timeDifference >= hour -> {
                    val hours = timeDifference / hour
                    String.format("%d saat", hours)
                }

                timeDifference >= minute -> {
                    val minutes = timeDifference / minute
                    String.format("%d dakika", minutes)
                }

                else -> {
                    val seconds = if (timeDifference == 0) 1 else timeDifference
                    String.format("%d saniye", seconds)
                }
            }
        } catch (e: Exception) {
            null
        }
    }

    inline fun <reified T> String.fromJson(): T? {
        return try {
            val type = object : TypeToken<T>() {}.type
            Gson().fromJson<T>(this, type)
        } catch (e: Exception) {
            null
        }
    }

    fun String.thousandSeparator(): String {
        val regex = "(\\d)(?=(\\d{3})+\$)".toRegex()
        return this.replace(regex, "\$1.")
    }

    fun String.getScreen(): Screen {
        val allScreens = Screen::class.java.kotlin.nestedClasses
            .filter { it.objectInstance != null }
            .mapNotNull { it.objectInstance }

        return (allScreens.firstOrNull { this.contains(it::class.simpleName.toString()) } as Screen?)
            ?: Screen.AuthSplash
    }
}