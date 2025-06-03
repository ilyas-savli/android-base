package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationsStatsResponse(
    @Json(name = "hasListingUnreadNotification")
    val hasListingUnreadNotification: Boolean?
)