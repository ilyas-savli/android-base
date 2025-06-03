package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountCourseIdResponse(
    @Json(name = "accountCourseId")
    val accountCourseId: String?
)