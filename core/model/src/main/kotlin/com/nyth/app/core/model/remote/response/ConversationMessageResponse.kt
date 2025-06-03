package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationMessageResponse (
    @Json(name = "id")
    val id: String,
    @Json(name = "senderId")
    val senderId: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "hasRead")
    val hasRead: Boolean,
    @Json(name = "createdAt")
    val createdAt: String
)
