package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventPostCreateResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "medias")
    val medias: List<String?>? = null
)