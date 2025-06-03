package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MostSearchedResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "desc")
    val desc: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
)