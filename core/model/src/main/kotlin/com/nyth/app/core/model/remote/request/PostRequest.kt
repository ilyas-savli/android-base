package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostRequest(
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "communities")
    val communities: List<String?>? = null,
    @Json(name = "medias")
    val medias: List<String?>? = null,
    @Json(name = "listing")
    val listing: Listing? = null,
    @Json(name = "shareType")
    val shareType: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class Listing(
        @Json(name = "classifiedAdNumber")
        val classifiedAdNumber: Long? = null,
        @Json(name = "url")
        val url: String? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "price")
        val price: Double? = null,
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "images")
        val images: List<String?>? = null,
        @Json(name = "tags")
        val tags: List<String?>? = null
    )
}