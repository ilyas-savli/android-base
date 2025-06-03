package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PutCommunityImageRequest(
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "imageType")
    val imageType: String? = null
) : Parcelable