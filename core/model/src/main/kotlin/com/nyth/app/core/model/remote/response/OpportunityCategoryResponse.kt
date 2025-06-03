package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpportunityCategoryResponse(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "mobileImageUrl")
    val mobileImageUrl: String? = null,
    @Json(name = "iconUrl")
    val iconUrl: String? = null,
    @Json(name = "isComingSoon")
    val isComingSoon: Boolean? = null,
    @Json(name = "slug")
    val slug: String? = null
)