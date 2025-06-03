package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class PostAcceleratePackageRequest (
    @Json(name = "acceleratePackagePriceId")
    val acceleratePackagePriceId: String,
    @Json(name = "period")
    val period: Period
) : Parcelable {
    @Parcelize
    enum class Period(val value: String) : Parcelable {
        Monthly("Monthly"),
        Weekly("Weekly");
        companion object  {
            fun fromString(value: String?): Period? {
                return values().firstOrNull() { it.value == value }
            }
        }

        fun toggle() : Period =
            when (this) {
                Monthly -> Weekly
                Weekly -> Monthly
            }
        fun isToggleOn(): Boolean =
            (this == Monthly)
    }
}