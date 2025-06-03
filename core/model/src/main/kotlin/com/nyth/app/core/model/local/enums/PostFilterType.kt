package com.nyth.app.core.model.local.enums

enum class PostFilterType(val type: String, val value: String) {
    ALL("All", "Tümü"),
    POST("Post", "Postlar"),
    EVENT("Event", "Etkinlikler"),
    ROUTE("Route", "Rotalar"),
    LISTING("Listing", "İlanlar"),
    COMMENTS("Comments", "Yanıtlar"),
    LIKES("Likes", "Beğeniler");

    companion object {
        fun fromType(type: String?): PostFilterType {
            return values().firstOrNull { it.type == type } ?: ALL
        }
    }
}