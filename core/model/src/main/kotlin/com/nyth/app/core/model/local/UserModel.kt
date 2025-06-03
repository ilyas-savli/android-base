package com.nyth.app.core.model.local

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("user_id")
    val userId: String? = null,

    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("profile_image_url")
    val profileImageUrl: String? = null
)
