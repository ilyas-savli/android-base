package com.nyth.app.core.model.remote.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BrandModelPopularResponse(
    @Json(name = "brandId")
    val brandId: String? = null,
    @Json(name = "brandModelId")
    val brandModelId: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null
)