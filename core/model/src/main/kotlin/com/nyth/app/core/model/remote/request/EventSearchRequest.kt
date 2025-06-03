package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventSearchRequest(
    @Json(name = "keyword")
    val keyword: String? = null,
    @Json(name = "pageIndex")
    val pageIndex: Int? = null,
    @Json(name = "pageSize")
    val pageSize: Int? = null
)