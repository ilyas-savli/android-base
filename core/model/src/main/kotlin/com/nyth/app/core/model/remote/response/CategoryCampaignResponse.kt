package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryCampaignResponse(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "categoryName")
    val categoryName: String? = null,
    @Json(name = "companyName")
    val companyName: String? = null,
    @Json(name = "companyImageUrl")
    val companyImageUrl: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "iconUrl")
    val iconUrl: String? = null,
    @Json(name = "url")
    val url: String? = null
)