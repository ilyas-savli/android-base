package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventPostResponse(
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "comments")
    val comments: List<PostResponse.Comment?>? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "hasLiked")
    val hasLiked: Boolean? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "likes")
    val likes: Int? = null,
    @Json(name = "postMedias")
    val postMedias: List<PostResponse.PostMedia?>? = null,
    @Json(name = "profilePicture")
    val profilePicture: String? = null,
    @Json(name = "username")
    val username: String? = null
)