package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SocialAccountMyListingsResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "classifiedAdNumber")
    val classifiedAdNumber: Long? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "images")
    val images: List<String>? = null,
    @Json(name = "tags")
    val tags: List<String?>? = null,
    @Json(name = "price")
    val price: Double? = null,
    @Json(name = "currency")
    val currency: String? = null
)