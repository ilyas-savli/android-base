package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventShareResponse(
    @Json(name = "postId")
    val postId: String? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "communities")
    val communities: List<String?>? = null
)