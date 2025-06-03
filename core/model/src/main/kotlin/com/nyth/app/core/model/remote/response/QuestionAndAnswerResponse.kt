package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionAndAnswerResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "question")
    val question: String? = null,
    @Json(name = "answer")
    val answer: String? = null,
    @Json(name = "listingOrder")
    val listingOrder: Int? = null
)