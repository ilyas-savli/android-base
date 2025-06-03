package com.nyth.app.core.model.remote.request

import com.nyth.app.core.model.local.enums.Gender
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PutChangeNameRequest(
    @Json(name = "firstName")
    val firstName: String? = null,
    @Json(name = "lastName")
    val lastName: String? = null,
    @Json(name = "username")
    val username: String? = null,
    @Json(name = "gender")
    val gender: Gender? = null,
)