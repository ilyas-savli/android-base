package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostHasNewResponse(

    @Json(name = "from")
    val from: String?,
    @Json(name = "count")
    val count: Int?

)