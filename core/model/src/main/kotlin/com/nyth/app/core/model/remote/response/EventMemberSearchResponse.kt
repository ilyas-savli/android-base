package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventMemberSearchResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "username")
    val username: String? = null,
    @Json(name = "profilePicture")
    val profilePicture: String? = null,
    @Json(name = "isMyFriend")
    val isMyFriend: Boolean? = null,
    @Json(name = "friendshipStatus")
    val friendshipStatus: String? = null
)