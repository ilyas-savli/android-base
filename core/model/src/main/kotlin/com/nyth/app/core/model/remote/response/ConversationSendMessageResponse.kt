package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationSendMessageResponse(
    @Json(name = "messageId")
    val messageId: String,
    @Json(name = "conversationId")
    val conversationId: String,
    @Json(name = "senderId")
    val senderId: String,
    @Json(name = "message")
    val message: String
)
