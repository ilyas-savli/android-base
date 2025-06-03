package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TokenRequest(
    @Json(name = "provider")
    val provider: String,
    @Json(name = "scope")
    val scope: String,
    @Json(name = "identity_token")
    val identityToken: String,
    @Json(name = "grant_type")
    val grantType: String,
    @Json(name = "client_secret")
    val clientSecret: String,
    @Json(name = "client_id")
    val clientId: String,
) : Parcelable