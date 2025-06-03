package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "firstName")
    val firstName: String? = null,
    @Json(name = "lastName")
    val lastName: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "emailConfirmed")
    val emailConfirmed: Boolean? = null,
    @Json(name = "phoneNumber")
    val phoneNumber: String? = null,
    @Json(name = "phoneNumberConfirmed")
    val phoneNumberConfirmed: Boolean? = null,
    @Json(name = "gender")
    val gender: String? = null,
    @Json(name = "lockoutEnabled")
    val lockoutEnabled: Boolean? = null,
    @Json(name = "lockoutEnd")
    val lockoutEnd: String? = null,
    @Json(name = "accountType")
    val accountType: String? = null,
    @Json(name = "accessFailedCount")
    val accessFailedCount: Int? = null,
    @Json(name = "profileImageUrl")
    val profileImageUrl: String? = null,
    @Json(name = "profileCoverImageUrl")
    val profileCoverImageUrl: String? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "username")
    val username: String? = null,
    @Json(name = "userName")
    val userName: String? = null,
    @Json(name = "location")
    val location: AccountResponse.Location? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Location(
        @Json(name = "city")
        val city: String? = null,
        @Json(name = "town")
        val town: String? = null
    )
}