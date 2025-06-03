package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubCategoryResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "parentCategoryId")
    val parentCategoryId: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "categoryType")
    val categoryType: String? = null,
    @Json(name = "hasChild")
    val hasChild: Boolean? = null
)