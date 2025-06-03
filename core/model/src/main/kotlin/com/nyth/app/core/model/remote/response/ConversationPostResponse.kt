package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationPostResponse(
    @Json(name = "conversationId")
    val conversationId: String? = null,
    @Json(name = "classifiedAdIdAccountId")
    val classifiedAdIdAccountId: String? = null,
)
