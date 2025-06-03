package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessoryResponse(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
)