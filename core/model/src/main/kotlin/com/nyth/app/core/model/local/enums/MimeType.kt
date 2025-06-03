package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MimeType(val type: String) : Parcelable {
    TextPlain(type = "text/plain"),
    TextHtml(type = "text/html"),
    Image(type = "image/*"),
    Video(type = "video/*");
}