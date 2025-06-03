package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityPostComplainResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "communityId")
    val communityId: String? = null,
    @Json(name = "postId")
    val postId: String? = null,
    @Json(name = "reason")
    val reason: String? = null,
    @Json(name = "status")
    val status: String? = null,
)