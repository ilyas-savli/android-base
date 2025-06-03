package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdvertResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "brandName")
    val brandName: String? = null,
    @Json(name = "modelName")
    val modelName: String? = null,
    @Json(name = "year")
    val year: String? = null,
    @Json(name = "km")
    val km: String? = null,
    @Json(name = "capacity")
    val capacity: String? = null,
    @Json(name = "price")
    val price: String? = null,
    @Json(name = "city")
    val city: String? = null,
    @Json(name = "isSponsored")
    val isSponsored: Boolean = false,
    @Json(name = "isBoostedAdvert")
    val isBoostedAdvert: Boolean = false,
    @Json(name = "size")
    val size: String? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "feature")
    val feature: String? = null,
)