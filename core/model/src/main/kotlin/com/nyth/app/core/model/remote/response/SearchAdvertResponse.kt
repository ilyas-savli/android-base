package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchAdvertResponse(
    @Json(name = "categoryId")
    val categoryId: String? = null,
    @Json(name = "categoryImageUrl")
    val categoryImageUrl: String? = null,
    @Json(name = "categoryType")
    val categoryType: String? = null,
    @Json(name = "count")
    val count: Int? = null,
    @Json(name = "items")
    val items: List<SearchedItem>? = emptyList()
)