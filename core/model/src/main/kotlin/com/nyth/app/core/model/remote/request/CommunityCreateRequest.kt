package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityCreateRequest(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "logoUrl")
    val logoUrl: String? = null,
    @Json(name = "coverUrl")
    val coverUrl: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "type")
    val type: String? = null,
)