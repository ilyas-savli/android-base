package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendProfileResponse(
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "userName")
    val userName: String? = null,
    @Json(name = "firstName")
    val firstName: String? = null,
    @Json(name = "lastName")
    val lastName: String? = null,
    @Json(name = "profilePhotoUrl")
    val profilePhotoUrl: String? = null,
    @Json(name = "coverPhotoUrl")
    val coverPhotoUrl: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "phone")
    val phone: String? = null,
    @Json(name = "hasRelation")
    val hasRelation: Boolean? = null,
    @Json(name = "requested")
    var requested: Boolean? = null,
    @Json(name = "isAcceptable")
    val isAcceptable: Boolean? = null
)