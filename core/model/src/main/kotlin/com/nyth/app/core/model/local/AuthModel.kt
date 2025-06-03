package com.nyth.app.core.model.local

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class AuthModel(
    val token: String?,

    @SerializedName("user_id")
    val userId: String?,

    @SerializedName("first_name")
    val firstName: String?,

    @SerializedName("last_name")
    val lastName: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("type")
    val type: String?
)