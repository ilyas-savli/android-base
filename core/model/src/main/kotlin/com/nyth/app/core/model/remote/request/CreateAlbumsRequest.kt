package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateAlbumsRequest(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "brand")
    val brand: String? = null,
)