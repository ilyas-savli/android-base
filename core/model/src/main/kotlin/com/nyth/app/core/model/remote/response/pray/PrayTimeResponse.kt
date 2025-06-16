package com.nyth.app.core.model.remote.response.pray

import android.icu.text.SimpleDateFormat
import java.util.Locale

data class PrayTimeResponse(
    val address: String?,
    val city: String?,
    val country: String?,
    val country_code: String?,
    val daylight: String?,
    val `for`: String?,
    val items: List<Item?>?,
    val latitude: String?,
    val link: String?,
    val longitude: String?,
    val map_image: String?,
    val method: Int?,
    val postal_code: String?,
    val prayer_method_name: String?,
    val qibla_direction: String?,
    val query: String?,
    val sealevel: String?,
    val state: String?,
    val status_code: Int?,
    val status_description: String?,
    val status_valid: Int?,
    val timezone: String?,
    val title: String?,
    val today_weather: TodayWeather?
) {
    data class Item(
        val asr: String?,
        val date_for: String?,
        val dhuhr: String?,
        val fajr: String?,
        val isha: String?,
        val maghrib: String?,
        val shurooq: String?
    )

    data class TodayWeather(
        val pressure: Int?,
        val temperature: String?
    )
}


data class PrayTimeUiModel(
    var prayTimes: ArrayList<PrayTimeItemUiModel>? = arrayListOf(),
    val qiblaDirection: String?,
)

data class PrayTimeItemUiModel(
    var timeName: String?,
    val time: String?,
)


fun PrayTimeResponse.toUiModel(): PrayTimeUiModel {
    val firstItem = this.items?.firstOrNull()

    val inputFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)  // AM/PM formatı
    val outputFormat = SimpleDateFormat("HH:mm", Locale("tr", "TR")) // 24 saatlik format

    fun convertTime(time: String?): String? {
        return try {
            time?.let {
                val date = inputFormat.parse(it)
                date?.let { outputFormat.format(it) }
            }
        } catch (e: Exception) {
            null
        }
    }

    val prayTimeList = arrayListOf<PrayTimeItemUiModel>().apply {
        firstItem?.fajr?.let { convertTime(it)?.let { t -> add(PrayTimeItemUiModel("İmsak", t)) } }
        firstItem?.shurooq?.let {
            convertTime(it)?.let { t ->
                add(
                    PrayTimeItemUiModel(
                        "Sabah",
                        t
                    )
                )
            }
        }
        firstItem?.dhuhr?.let { convertTime(it)?.let { t -> add(PrayTimeItemUiModel("Öğle", t)) } }
        firstItem?.asr?.let { convertTime(it)?.let { t -> add(PrayTimeItemUiModel("İkindi", t)) } }
        firstItem?.maghrib?.let {
            convertTime(it)?.let { t ->
                add(
                    PrayTimeItemUiModel(
                        "Akşam",
                        t
                    )
                )
            }
        }
        firstItem?.isha?.let { convertTime(it)?.let { t -> add(PrayTimeItemUiModel("Yatsı", t)) } }
    }

    return PrayTimeUiModel(
        prayTimes = prayTimeList,
        qiblaDirection = this.qibla_direction
    )
}