package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoriteListsResponse(
    @Json(name = "coverImage")
    val coverImage: String? = null,
    @Json(name = "emailNotification")
    val emailNotification: Boolean? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "listingCount")
    val listingCount: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "smsNotification")
    val smsNotification: Boolean? = null
)