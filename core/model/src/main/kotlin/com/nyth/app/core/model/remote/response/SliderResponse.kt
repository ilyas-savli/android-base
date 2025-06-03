package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SliderResponse(
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "mobileImageUrl")
    val mobileImageUrl: String? = null,
    @Json(name = "order")
    val order: Int? = null
)
