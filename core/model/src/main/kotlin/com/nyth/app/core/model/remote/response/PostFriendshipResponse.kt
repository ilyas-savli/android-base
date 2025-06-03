package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostFriendshipResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "fromAccountId")
    val fromAccountId: String? = null,
    @Json(name = "fromFullName")
    val fromFullName: String? = null,
    @Json(name = "fromProfilePicture")
    val fromProfilePicture: String? = null,
    @Json(name = "fromUserName")
    val fromUserName: String? = null,
    @Json(name = "toAccountId")
    val toAccountId: String? = null,
    @Json(name = "toFullName")
    val toFullName: String? = null,
    @Json(name = "toProfilePicture")
    val toProfilePicture: String? = null,
    @Json(name = "toUserName")
    val toUserName: String? = null,
    @Json(name = "status")
    val status: String? = null
)