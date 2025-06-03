package com.nyth.app.core.model.local.enums

enum class PostFilterLabelType(val type: String, val value: String) {
    ALL("All", "Tümü"),
    POST("Post", "Post"),
    EVENT("Event", "Etkinlik"),
    ROUTE("Route", "Rota"),
    LISTING("Listing", "İlan"),
    COMMENTS("Comments", "Yanıt"),
    LIKES("Likes", "Beğeni");

    companion object {
        fun fromType(type: String?): PostFilterLabelType {
            return values().firstOrNull { it.type == type } ?: ALL
        }
    }
}