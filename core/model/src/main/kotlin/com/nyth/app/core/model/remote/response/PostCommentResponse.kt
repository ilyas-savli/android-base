package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostCommentResponse(
    @Json(name = "commentId")
    val commentId: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "fullName")
    val fullName: String?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "postId")
    val postId: String?,
    @Json(name = "profilePicture")
    val profilePicture: String?,
    @Json(name = "username")
    val username: String?
)