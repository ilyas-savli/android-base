package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class RelatedLinkType(val type: String) : Parcelable {
    Models(type = "models"),
    Cities(type = "cities"),
    Brands(type = "brands"),
    EngineTypes(type = "engineTypes"),
    MotorcycleTypes(type = "motorcycleTypes"),
}