package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PutNotificationPermissionRequest(
    @Json(name = "accountNotificationPermissionId")
    val accountNotificationPermissionId: String? = null,
    @Json(name = "email")
    val email: Boolean? = null,
    @Json(name = "mobilePush")
    val mobilePush: Boolean? = null
) : Parcelable