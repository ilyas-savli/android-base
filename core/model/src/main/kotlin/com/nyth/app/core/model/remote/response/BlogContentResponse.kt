package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlogContentResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "body")
    val body: String? = null,
    @Json(name = "coverImageUrl")
    val coverImageUrl: String? = null,
    @Json(name = "slug")
    val slug: String? = null,
    @Json(name = "readingTime")
    val readingTime: Int? = null,
    @Json(name = "publishedDate")
    val publishedDate: String? = null,
    @Json(name = "reaction")
    val reaction: Reaction? = null,
    @Json(name = "author")
    val author: Author? = null,
    @Json(name = "category")
    val category: Category? = null
) {
    @JsonClass(generateAdapter = true)
    data class Reaction(
        @Json(name = "speedy")
        val speedy: Int? = null,
        @Json(name = "passionate")
        val passionate: Int? = null,
        @Json(name = "innovator")
        val innovator: Int? = null,
        @Json(name = "cheerful")
        val cheerful: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Author(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "surname")
        val surname: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null
    )
}