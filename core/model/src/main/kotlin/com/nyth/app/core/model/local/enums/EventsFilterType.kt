package com.nyth.app.core.model.local.enums

enum class EventsFilterType(val type: String, val title: String) {
    All(type = "All", "Tümü"),
    Current(type = "Current", "Güncel"),
    Past(type = "Past", "Geçmiş"),
    Upcoming(type = "Upcoming", "Yakında");

    companion object {
        fun fromType(type: String?): EventsFilterType? = values().find { it.type == type }
    }
}