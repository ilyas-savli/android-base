package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingNotificationPutRequest(
    @Json(name = "emailNotification")
    val emailNotification: Boolean? = null,
    @Json(name = "pushNotification")
    val pushNotification: Boolean? = null,
    @Json(name = "hasPriceLimit")
    val hasPriceLimit: Boolean? = null,
    @Json(name = "priceLimit")
    val priceLimit: Int? = null,
    @Json(name = "isEnabled")
    val isEnabled: Boolean? = null
)