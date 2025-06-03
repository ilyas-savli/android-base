package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcceleratePackageResponse (
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "isPopular") val isPopular: Boolean? = null,
    @Json(name = "acceleratePlace") val acceleratePlace: String? = null,
    @Json(name = "features") val features: List<String>? = null,
    @Json(name = "monthlyPrice") val monthlyPrice: Price? = null,
    @Json(name = "weeklyPrice") val weeklyPrice: Price? = null,
    @Json(name = "order") val order: Int? = null,
    @Json(name = "required") val required: Boolean? = null,
    @Json(name = "selected") var selected: Boolean? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency") val currency: String? = null,
        @Json(name = "amount") val amount: Double? = null
    )
}