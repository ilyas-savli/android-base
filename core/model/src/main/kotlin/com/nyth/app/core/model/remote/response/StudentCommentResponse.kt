package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudentCommentResponse(
    @Json(name = "comment")
    val comment: String?,
    @Json(name = "starCount")
    val starCount: Double?,
    @Json(name = "studentFullName")
    val studentFullName: String?,
    @Json(name = "studentImageUrl")
    val studentImageUrl: String?,
    @Json(name = "studentTitle")
    val studentTitle: String?
)