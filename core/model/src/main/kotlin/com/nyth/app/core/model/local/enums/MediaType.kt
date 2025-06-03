package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MediaType(val type: String, var extension: String? = null) : Parcelable {
    Image(type = "Image"),
    Video(type = "Video"),
    EngineVideo(type = "EngineVideo"),
    None(type = "None");

    companion object {
        fun fromString(type: String?): MediaType {
            return values().firstOrNull { it.type == type } ?: None
        }
    }
}