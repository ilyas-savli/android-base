package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PostFavoriteListsRequest(
    @Json(name = "classifiedAdId")
    val classifiedAdId: String? = null
) : Parcelable