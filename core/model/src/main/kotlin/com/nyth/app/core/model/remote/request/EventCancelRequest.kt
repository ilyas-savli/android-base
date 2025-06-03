package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventCancelRequest(
    @Json(name = "reason")
    val reason: String? = null
)