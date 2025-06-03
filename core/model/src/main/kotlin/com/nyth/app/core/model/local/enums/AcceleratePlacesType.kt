package com.nyth.app.core.model.local.enums

enum class AcceleratePlacesType(val type: String) {
    ShowInCategoryPage(type = "ShowInCategoryPage"),
    Showcase(type = "Showcase"),
    MakeDifferent(type = "MakeDifferent"),
    ShowAtTop(type = "ShowAtTop"),
    ShowInExplore(type = "ShowInExplore");

    companion object {
        fun fromType(type: String?): AcceleratePlacesType? = values().find { it.type == type }
    }
}