package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeCompareResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "firstBrand")
    val firstBrand: String? = null,
    @Json(name = "firstModel")
    val firstModel: String? = null,
    @Json(name = "secondBrand")
    val secondBrand: String? = null,
    @Json(name = "secondModel")
    val secondModel: String? = null,
)