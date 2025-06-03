package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityByIdResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "logoUrl")
    val logoUrl: String? = null,
    @Json(name = "coverImageUrl")
    val coverImageUrl: String? = null,
    @Json(name = "imagePosition")
    val imagePosition: İmagePosition? = null,
    @Json(name = "privacyType")
    val privacyType: String? = null,
    @Json(name = "membersCount")
    val membersCount: Int? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "isModerator")
    val isModerator: Boolean? = null,
    @Json(name = "requested")
    val requested: Boolean? = null,
    @Json(name = "joined")
    val joined: Boolean? = null
) {
    @JsonClass(generateAdapter = true)
    data class İmagePosition(
        @Json(name = "top")
        val top: Int? = null,
        @Json(name = "right")
        val right: Int? = null,
        @Json(name = "bottom")
        val bottom: Int? = null,
        @Json(name = "left")
        val left: Int? = null
    )
}