package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingMediaPutRequest(
    @Json(name = "isCover")
    val isCover: Boolean? = null,
    @Json(name = "order")
    val order: Int? = null,
    @Json(name = "mediaType")
    val mediaType: String? = null
)