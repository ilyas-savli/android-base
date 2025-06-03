package com.nyth.app.core.designsystem.ext

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.webkit.MimeTypeMap
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object UtilExt {

    fun Context.getFileFromContentResolver(contentUri: Uri): File? {
        val contentResolver: ContentResolver = contentResolver
        val cacheDir = File(cacheDir, "temp_files").apply { mkdirs() }  // Ensure directory exists

        return try {
            // Open an input stream for the content URI
            val inputStream = contentResolver.openInputStream(contentUri)
            inputStream?.use { input ->
                // Generate a valid filename
                var fileName = System.currentTimeMillis().toString()
                this.getMimeTypeFromUri(contentUri)?.let { mimeType ->
                    val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
                    if (extension != null) {
                        fileName = "$fileName.$extension"
                    }
                }
                // Create a temporary file to store the content
                val tempFile = File(cacheDir, fileName)
                if (!tempFile.exists()) {
                    val created = tempFile.createNewFile()
                    Timber.d("created: $created")
                }
                // Write the content from the input stream to the temporary file
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
                tempFile
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
    fun Context.getMimeTypeFromUri(uri: Uri): String? {
        return if (uri.scheme.equals(ContentResolver.SCHEME_CONTENT)) {
            val contentResolver: ContentResolver = contentResolver
            contentResolver.getType(uri)
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.lowercase())
        }
    }
}