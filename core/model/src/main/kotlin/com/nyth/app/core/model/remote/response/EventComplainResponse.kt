package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventComplainResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "eventId")
    val eventId: String? = null,
    @Json(name = "reason")
    val reason: String? = null,
    @Json(name = "status")
    val status: String? = null
)