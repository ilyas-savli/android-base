package com.nyth.app.core.model.remote.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationPermissionResponse(
    @Json(name = "categoryName")
    val categoryName: String? = null,
    @Json(name = "permissions")
    val permissions: List<Permission?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Permission(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "email")
        val email: Boolean? = null,
        @Json(name = "mobile")
        val mobile: Boolean? = null
    )
}