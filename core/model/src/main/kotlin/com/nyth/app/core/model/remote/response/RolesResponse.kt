package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class RolesResponse : ArrayList<RolesResponse.RoleResponseItem>() {
    @JsonClass(generateAdapter = true)
    data class RoleResponseItem(
        @Json(name = "id")
        val id: String?,
        @Json(name = "name")
        val name: String?
    )
}