package com.nyth.app.core.model.remote.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesPopularResponse(
    @Json(name = "categoryId")
    val categoryId: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null
)