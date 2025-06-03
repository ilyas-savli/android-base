package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunitiesMembersSearchResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "accountFullName")
    val accountFullName: String? = null,
    @Json(name = "userName")
    val userName: String? = null,
    @Json(name = "profilePictureUrl")
    val profilePictureUrl: String? = null,
    @Json(name = "isMyFriend")
    val isMyFriend: Boolean? = null,
    @Json(name = "friendshipStatus")
    val friendshipStatus: String? = null,
    @Json(name = "isOwner")
    val isOwner: Boolean? = null,
)