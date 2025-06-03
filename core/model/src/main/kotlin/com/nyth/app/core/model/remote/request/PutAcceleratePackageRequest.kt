package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PutAcceleratePackageRequest(
    @Json(name = "acceleratePlace")
    val acceleratePlace: List<String>,
    @Json(name = "period")
    val period: Period
) : Parcelable {
    @Parcelize
    enum class Period(val value: String) : Parcelable {
        @Json(name = "Monthly")
        Monthly("Monthly"),
        @Json(name = "Weekly")
        Weekly("Weekly");

        fun toggle(): Period =
            when (this) {
                Monthly -> Weekly
                Weekly -> Monthly
            }

        fun isToggleOn(): Boolean =
            (this == Monthly)
    }
}