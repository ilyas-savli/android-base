package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchedItem(
    @Json(name = "classifiedAdNumber")
    val classifiedAdNumber: Int?,
    @Json(name = "count")
    val count: Int?,
    @Json(name = "filter")
    val filter: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "id")
    val id: String?
)