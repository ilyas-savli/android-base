package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JustWeSearchResponse(
    @Json(name = "totalPosts")
    val totalPosts: Int? = null,
    @Json(name = "totalEvents")
    val totalEvents: Int? = null,
    @Json(name = "totalCommunities")
    val totalCommunities: Int? = null,
    @Json(name = "posts")
    val posts: List<Post?>? = null,
    @Json(name = "events")
    val events: List<Event?>? = null,
    @Json(name = "communities")
    val communities: List<Community?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Post(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "content")
        val content: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Event(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "description")
        val description: String? = null,
        @Json(name = "type")
        val type: String? = null,
        @Json(name = "location")
        val location: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Community(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null
    )
}