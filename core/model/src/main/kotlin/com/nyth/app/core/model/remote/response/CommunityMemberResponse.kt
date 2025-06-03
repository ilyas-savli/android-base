package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityMemberResponse(
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "profilePhoto")
    val profilePhoto: String? = null,
    @Json(name = "userName")
    val userName: String? = null,
    @Json(name = "isMyFriend")
    val isMyFriend: Boolean? = null,
    @Json(name = "friendshipStatus")
    val friendshipStatus: String? = null,
    @Json(name = "isOwner")
    val isOwner: Boolean? = null
)