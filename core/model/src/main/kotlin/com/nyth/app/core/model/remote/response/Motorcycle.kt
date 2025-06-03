package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Motorcycle(
    @Json(name = "brand")
    val brand: String? = null,
    @Json(name = "brandImageUrl")
    val brandImageUrl: String? = null,
    @Json(name = "brandModelId")
    val brandModelId: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "model")
    val model: String? = null,
    @Json(name = "motorcycleId")
    val motorcycleId: String? = null,
    @Json(name = "rate")
    val rate: Int? = null,
    @Json(name = "voteCount")
    val voteCount: Int? = null
)