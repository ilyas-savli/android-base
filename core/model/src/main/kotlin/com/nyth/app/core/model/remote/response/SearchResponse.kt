package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "categoryName")
    val categoryName : String? = null,
    @Json(name = "brandName")
    val brandName : String? = null,
    @Json(name = "modelName")
    val modelName : String? = null,
    @Json(name = "count")
    val count : String? = null,
)
