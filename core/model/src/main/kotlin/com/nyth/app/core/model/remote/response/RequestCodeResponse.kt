package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestCodeResponse(
    @Json(name = "codeTimeOut")
    val codeTimeOut: Int? = null
)