package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationMessage(
    @Json(name = "ConversationMessageId")
    val conversationMessageId: String? = null,
    @Json(name = "ConversationId")
    val conversationId: String? = null,
    @Json(name = "FromAccountId")
    val fromAccountId: String? = null,
    @Json(name = "ToAccountId")
    val toAccountId: String? = null,
    @Json(name = "Message")
    val message: String? = null,
    @Json(name = "Read")
    val read: Boolean? = null,
    @Json(name = "ReadUtc")
    val readUtc: String? = null,
    @Json(name = "CreatedUtc")
    val createdUtc: String? = null,
    @Json(name = "FromImageUrl")
    val fromImageUrl: String? = null,
    @Json(name = "FromAccountName")
    val fromAccountName: String? = null,
)
