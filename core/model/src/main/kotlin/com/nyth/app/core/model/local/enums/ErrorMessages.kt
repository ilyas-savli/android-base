package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ErrorMessages(val errorMessage: String, val message: String) : Parcelable {
    InvalidToken(errorMessage = "Invalid token.", message = "Geçersiz Kod");

    companion object {
        fun find(errorMessage: String?) = ErrorMessages.values().firstOrNull { it.errorMessage == errorMessage }
    }
}