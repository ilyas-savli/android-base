package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountCourseResponse(
    @Json(name = "id")
    val id: String?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "totalDuration")
    val totalDuration: TotalDuration?,
    @Json(name = "totalStudentCount")
    val totalStudentCount: Int?
)