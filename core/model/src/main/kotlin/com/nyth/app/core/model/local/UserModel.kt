package com.nyth.app.core.model.local

import com.squareup.moshi.Json

data class UserModel(
    @Json(name = "user_id")
    val userId: String? = null,

    @Json(name = "first_name")
    val firstName: String? = null,

    @Json(name = "last_name")
    val lastName: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "profile_image_url")
    val profileImageUrl: String? = null
)