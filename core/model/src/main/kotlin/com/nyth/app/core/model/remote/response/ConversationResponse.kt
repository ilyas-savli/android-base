package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversationResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "classifiedAdId")
    val classifiedAdId: String? = null,
    @Json(name = "classifiedAdAccountId")
    val classifiedAdAccountId: String? = null,
    @Json(name = "fromAccountId")
    val fromAccountId: String? = null,
    @Json(name = "listingImageUrl")
    val listingImageUrl: String? = null,
    @Json(name = "listingTitle")
    val listingTitle: String? = null,
    @Json(name = "accountImageUrl")
    val accountImageUrl: String? = null,
    @Json(name = "accountUserName")
    val accountUserName: String? = null,
    @Json(name = "unReadCount")
    val unReadCount: Int = -1
)
