package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PutFavoriteListsRequest(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "smsNotification")
    val smsNotification: Boolean? = null,
    @Json(name = "emailNotification")
    val emailNotification: Boolean? = null,
)