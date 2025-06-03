package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventPostCommentResponse(
    @Json(name = "eventPostId")
    val eventPostId: String? = null,
    @Json(name = "commentId")
    val commentId: String? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "profilePicture")
    val profilePicture: String? = null,
    @Json(name = "username")
    val username: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null
)