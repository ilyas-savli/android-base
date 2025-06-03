package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ExternalRegisterRequest(
    @Json(name = "provider")
    val provider: String,
    @Json(name = "idToken")
    val idToken: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "privacyPolicyVersion")
    val privacyPolicyVersion: String,
    @Json(name = "agreement")
    val agreement: Boolean? = null,
    @Json(name = "communicationPermission")
    val communicationPermission: Boolean? = null
) : Parcelable