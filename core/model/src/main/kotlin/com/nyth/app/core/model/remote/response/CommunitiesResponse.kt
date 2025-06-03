package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunitiesResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "logoUrl")
    val logoUrl: String? = null,
    @Json(name = "coverUrl")
    val coverUrl: String? = null,
    @Json(name = "membersCount")
    val membersCount: Int? = null,
    @Json(name = "joined")
    var joined: Boolean? = null,
    @Json(name = "privacyType")
    val privacyType: String? = null,
    @Json(name = "avatars")
    val avatars: List<String>? = null,
    @Json(name = "isSelected")
    var isSelected: Boolean? = false
)