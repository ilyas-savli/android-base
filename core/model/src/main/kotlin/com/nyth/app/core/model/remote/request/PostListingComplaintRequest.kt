package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostListingComplaintRequest(
    @Json(name = "classifiedAdId")
    val classifiedAdId: String?,
    @Json(name = "reasonTypeId")
    val reasonTypeId: Int?,
    @Json(name = "reason")
    val reason: String?
)