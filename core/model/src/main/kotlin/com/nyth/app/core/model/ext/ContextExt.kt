package com.nyth.app.core.model.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import com.nyth.app.core.model.local.enums.MediaType
import com.nyth.app.core.model.local.enums.MimeType
import com.nyth.app.core.model.utils.AppConstants

object ContextExt {
    fun Context.getMediaType(uri: Uri): MediaType {
        val mimeType = contentResolver.getType(uri)
        val mediaType = when {
            mimeType?.startsWith("image/") == true -> MediaType.Image
            mimeType?.startsWith("video/") == true -> MediaType.Video
            else -> MediaType.None
        }

        val mime = MimeTypeMap.getSingleton()
        mediaType.extension = mime.getExtensionFromMimeType(mimeType)
        return mediaType
    }

    fun Context.shareLink(url: String) {
        val sendIntent = Intent(
            Intent.ACTION_SEND
        ).apply {
            putExtra(Intent.EXTRA_TEXT, url)
            type = MimeType.TextPlain.type
        }
        val shareIntent = Intent.createChooser(
            sendIntent, null
        )
        startActivity(shareIntent)
    }

    fun Context.sendMessageFromWhatsApp(phoneNumber: String, message: String, onError: () -> Unit) {
        val encodedMessage = Uri.encode(message)
        val wpUriFormat = String.format(AppConstants.WHATSAPP_URI_FORMAT, phoneNumber, encodedMessage)
        val uri = Uri.parse(wpUriFormat)
        val whatsappIntent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(whatsappIntent)
        } catch (e: Exception) {
            onError()
        }
    }

    fun Context.openUrl(url: String?) {
        if (!url.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.startActivity(intent)
        } else {
            return
        }
    }
}