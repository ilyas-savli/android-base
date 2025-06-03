package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventPostCreateRequest(
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "medias")
    val medias: List<String?>? = null
)