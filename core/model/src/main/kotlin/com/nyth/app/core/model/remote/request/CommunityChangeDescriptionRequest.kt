package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityChangeDescriptionRequest(
    @Json(name = "description")
    val description: String? = null,
)