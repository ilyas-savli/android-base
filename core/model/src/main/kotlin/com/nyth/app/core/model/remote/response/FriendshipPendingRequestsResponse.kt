package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendshipPendingRequestsResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "profilePicture")
    val profilePicture: String? = null,
    @Json(name = "userName")
    val userName: String? = null,
    @Json(name = "status")
    val status: String? = null
)