package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlogContentOfDayResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "thumbnailImageUrl")
    val thumbnailImageUrl: String? = null,
    @Json(name = "slug")
    val slug: String? = null,
    @Json(name = "readingTime")
    val readingTime: Int? = null,
    @Json(name = "publishedDate")
    val publishedDate: String? = null,
    @Json(name = "author")
    val author: Author? = null,
    @Json(name = "category")
    val category: Category? = null
) {
    @JsonClass(generateAdapter = true)
    data class Author(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "surname")
        val surname: String? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null
    )
}