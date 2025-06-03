package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountCommunitiesResponse(
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
    val joined: Boolean? = null,
    @Json(name = "privacyType")
    val privacyType: String? = null
)