package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PutCommunicationNotificationRequest(
    @Json(name = "emailNotification")
    val emailNotification: Boolean? = null,
    @Json(name = "pushNotification")
    val pushNotification: Boolean? = null,
    @Json(name = "notificationType")
    val notificationType: String? = null
)