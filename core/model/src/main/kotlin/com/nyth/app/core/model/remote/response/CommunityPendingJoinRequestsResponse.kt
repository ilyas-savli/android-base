package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityPendingJoinRequestsResponse(
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "accountFullName")
    val accountFullName: String? = null,
    @Json(name = "profilePictureUrl")
    val profilePictureUrl: String? = null,
    @Json(name = "userName")
    val userName: String? = null,
)