package com.nyth.app.core.model.local

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class AuthModel(
    val token: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "sub")
    val sub: String? = null,

    @Json(name = "admin")
    val admin: Boolean? = null,

    @Json(name = "iat")
    val iat: Long? = null
)