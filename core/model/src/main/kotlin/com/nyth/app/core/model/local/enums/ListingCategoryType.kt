package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ListingCategoryType(val type: String) : Parcelable {
    Motor(type = "Motorcycle"),
    Part(type = "Parts"),
    Accessory(type = "Accessory");

    companion object {
        fun find(type: String?) = values().firstOrNull { it.type == type }
        fun findNullable(type: String?) = values().firstOrNull { it.type == type }
    }
}