package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ListingVehicleType(val type: String) : Parcelable {
    Naked(type = "Naked"),
    Scooter(type = "Scooter"),
    SuperSport(type = "Super Suport"),
    Enduro(type = "Enduro");

    companion object {
        fun find(type: String?) = values().firstOrNull { it.type == type } ?: Naked
    }
}