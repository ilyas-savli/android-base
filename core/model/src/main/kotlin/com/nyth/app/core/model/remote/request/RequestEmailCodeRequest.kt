package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestEmailCodeRequest(
    @Json(name = "newEmail")
    val newEmail: String? = null
)