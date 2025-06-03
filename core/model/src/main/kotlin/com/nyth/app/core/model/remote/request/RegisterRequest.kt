package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.nyth.app.core.model.local.enums.Gender
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "firstName")
    val firstName: String? = null,
    @Json(name = "lastName")
    val lastName: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "phone")
    val phone: String? = null,
    @Json(name = "gender")
    val gender: Gender? = null,
    @Json(name = "agreement")
    val agreement: Boolean? = null,
    @Json(name = "communicationPermission")
    val communicationPermission: Boolean? = null
) : Parcelable