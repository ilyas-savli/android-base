package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "bigCoverPhoto")
    val bigCoverPhoto: Boolean? = null,
    @Json(name = "categoryType")
    val categoryType: String? = null,
    @Json(name = "hasChild")
    val hasChild: Boolean? = null
)