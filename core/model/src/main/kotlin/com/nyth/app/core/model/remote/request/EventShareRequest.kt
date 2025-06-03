package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventShareRequest(
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "communities")
    val communities: List<String?>? = null
)