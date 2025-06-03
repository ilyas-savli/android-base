package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DeleteNotificationRequest (
    @Json(name = "notificationId")
    val notificationId: List<String>
)
