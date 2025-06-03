package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAlbumsResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "brand")
    val brand: String? = null,
    @Json(name = "isDefault")
    val isDefault: Boolean? = null,
    @Json(name = "totalImages")
    val totalImages: Int? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "coverUrl")
    val coverUrl: String? = null
)