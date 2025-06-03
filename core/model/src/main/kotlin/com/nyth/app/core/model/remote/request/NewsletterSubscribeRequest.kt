package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsletterSubscribeRequest(
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "moduleType")
    val moduleType: String? = null,
    @Json(name = "communicationPermission")
    val communicationPermission: Boolean? = null,
)