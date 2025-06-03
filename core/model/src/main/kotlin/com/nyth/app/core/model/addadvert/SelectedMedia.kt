package com.nyth.app.core.model.addadvert

import android.net.Uri
import android.os.Parcelable
import com.nyth.app.core.model.local.enums.MediaType
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class SelectedMedia(
    val mediaUri: Uri? = null,
    val isCover: Boolean = false,
    val isAsMotorVoice: Boolean = false,
    val file: File? = null,
    val mediaType: MediaType = MediaType.Image,
    val mediaId: String? = null,
    val mediaUrl: String? = null,
    var isUploaded: Boolean = false,
    val order: Int? = 0,
    val isChanged: Boolean? = false
) : Parcelable {
    fun getUrl(): Any? = if (isUploaded) mediaUrl else mediaUri

    fun findMediaType(): MediaType = if (isAsMotorVoice && mediaType == MediaType.Video) MediaType.EngineVideo else mediaType
}