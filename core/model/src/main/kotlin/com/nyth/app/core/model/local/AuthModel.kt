package com.nyth.app.core.model.local

import com.squareup.moshi.Json

data class AuthModel(
    val token: String?,

    @Json(name = "user_id")
    val userId: String?,

    @Json(name = "first_name")
    val firstName: String?,

    @Json(name = "last_name")
    val lastName: String?,

    @Json(name = "email")
    val email: String?,

    @Json(name = "type")
    val type: String?
)