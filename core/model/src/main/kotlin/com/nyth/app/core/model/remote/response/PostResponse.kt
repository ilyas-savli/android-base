package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "fullName")
    var fullName: String? = null,
    @Json(name = "profilePicture")
    val profilePicture: String? = null,
    @Json(name = "username")
    val username: String? = null,
    @Json(name = "hasLiked")
    val hasLiked: Boolean? = null,
    @Json(name = "likes")
    val likes: Int? = null,
    @Json(name = "shareType")
    val shareType: String? = null,
    @Json(name = "postType")
    val postType: String? = null,
    @Json(name = "classifiedAd")
    val classifiedAd: ClassifiedAd? = null,
    @Json(name = "routePost")
    val routePost: RoutePost? = null,
    @Json(name = "event")
    val event: Event? = null,
    @Json(name = "totalComments")
    var totalComments: Int? = null,
    @Json(name = "comments")
    var comments: List<Comment?>? = null,
    @Json(name = "postCommunities")
    val postCommunities: List<PostCommunity?>? = null,
    @Json(name = "postMedia")
    val postMedia: List<PostMedia?>? = null,
    @Json(name = "createdAt")
    val createdAt: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class ClassifiedAd(
        @Json(name = "number")
        val number: Int? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "price")
        val price: Int? = null,
        @Json(name = "url")
        val url: String? = null,
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "images")
        val images: List<String?>? = null,
        @Json(name = "tags")
        val tags: List<String?>? = null,
        @Json(name = "id")
        val id: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class RoutePost(
        @Json(name = "distance")
        val distance: String? = null,
        @Json(name = "from")
        val from: String? = null,
        @Json(name = "to")
        val to: String? = null,
        @Json(name = "date")
        val date: String? = null,
        @Json(name = "id")
        val id: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Event(
        @Json(name = "ownerId")
        val ownerId: String? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "description")
        val description: String? = null,
        @Json(name = "type")
        val type: String? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null,
        @Json(name = "location")
        val location: String? = null,
        @Json(name = "startDate")
        val startDate: String? = null,
        @Json(name = "endDate")
        val endDate: String? = null,
        @Json(name = "accountEvents")
        val accountEvents: List<AccountEvent?>? = null,
        @Json(name = "eventPosts")
        val eventPosts: List<EventPost?>? = null,
        @Json(name = "createdBy")
        val createdBy: String? = null,
        @Json(name = "createdAt")
        val createdAt: String? = null,
        @Json(name = "id")
        val id: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class AccountEvent(
            @Json(name = "accountId")
            val accountId: String? = null,
            @Json(name = "fullName")
            val fullName: String? = null,
            @Json(name = "userName")
            val userName: String? = null,
            @Json(name = "profilePicture")
            val profilePicture: String? = null,
            @Json(name = "id")
            val id: String? = null
        )

        @JsonClass(generateAdapter = true)
        data class EventPost(
            @Json(name = "accountId")
            val accountId: String? = null,
            @Json(name = "content")
            val content: String? = null,
            @Json(name = "fullName")
            val fullName: String? = null,
            @Json(name = "profilePicture")
            val profilePicture: String? = null,
            @Json(name = "username")
            val username: String? = null,
            @Json(name = "postMedias")
            val postMedias: List<PostMedia?>? = null,
            @Json(name = "postLikes")
            val postLikes: List<PostLike?>? = null,
            @Json(name = "comments")
            val comments: List<Comment?>? = null,
            @Json(name = "createdAt")
            val createdAt: String? = null,
            @Json(name = "id")
            val id: String? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class PostMedia(
                @Json(name = "url")
                val url: String? = null,
                @Json(name = "id")
                val id: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class PostLike(
                @Json(name = "accountId")
                val accountId: String? = null,
                @Json(name = "likeDate")
                val likeDate: String? = null,
                @Json(name = "id")
                val id: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class Comment(
                @Json(name = "accountId")
                val accountId: String? = null,
                @Json(name = "fullName")
                val fullName: String? = null,
                @Json(name = "profilePicture")
                val profilePicture: String? = null,
                @Json(name = "username")
                val username: String? = null,
                @Json(name = "parentCommentId")
                val parentCommentId: String? = null,
                @Json(name = "message")
                val message: String? = null,
                @Json(name = "comments")
                val comments: List<Comment?>? = null,
                @Json(name = "commentLikes")
                val commentLikes: List<CommentLike?>? = null,
                @Json(name = "createdAt")
                val createdAt: String? = null,
                @Json(name = "id")
                val id: String? = null
            ) {
                @JsonClass(generateAdapter = true)
                data class CommentLike(
                    @Json(name = "accountId")
                    val accountId: String? = null,
                    @Json(name = "likeDate")
                    val likeDate: String? = null,
                    @Json(name = "id")
                    val id: String? = null
                )
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Comment(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "accountId")
        val accountId: String? = null,
        @Json(name = "fullName")
        val fullName: String? = null,
        @Json(name = "profilePicture")
        val profilePicture: String? = null,
        @Json(name = "username")
        val username: String? = null,
        @Json(name = "parentCommentId")
        val parentCommentId: String? = null,
        @Json(name = "message")
        val message: String? = null,
        @Json(name = "hasLiked")
        val hasLiked: Boolean? = null,
        @Json(name = "likes")
        val likes: Int? = null,
        @Json(name = "comments")
        val comments: List<Comment?>? = null,
        @Json(name = "createdAt")
        val createdAt: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class PostCommunity(
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "id")
        val id: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class PostMedia(
        @Json(name = "url")
        val url: String? = null,
        @Json(name = "id")
        val id: String? = null
    )
}