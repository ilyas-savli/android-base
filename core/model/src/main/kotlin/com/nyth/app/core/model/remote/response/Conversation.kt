package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Conversation(
    @Json(name = "ConversationId")
    var conversationId: String? = null,
    @Json(name = "FromAccountId")
    var fromAccountId: String? = null,
    @Json(name = "FromFullName")
    val fromFullName: String? = null,
    @Json(name = "FromImageUrl")
    val fromImageUrl: String? = null,
    @Json(name = "ToAccountId")
    val toAccountId: String? = null,
    @Json(name = "ToFullName")
    var toFullName: String? = null,
    @Json(name = "ToImageUrl")
    val toImageUrl: String? = null,
    @Json(name = "FromLastDeletedUtc")
    val fromLastDeletedUtc: String? = null,
    @Json(name = "ToLastDeletedUtc")
    val toLastDeletedUtc: String? = null,
    @Json(name = "UnreadMessageCount")
    var unreadMessageCount: Int? = null,
)

