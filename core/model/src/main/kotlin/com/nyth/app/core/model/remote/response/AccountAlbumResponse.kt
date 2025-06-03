package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountAlbumResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "brand")
    val brand: String? = null,
    @Json(name = "images")
    val images: List<Image?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Image(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null
    )
}