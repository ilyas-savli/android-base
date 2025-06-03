package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "isCover")
    val isCover: Boolean? = null,
    @Json(name = "order")
    val order: Int? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "url")
    val url: String? = null
)