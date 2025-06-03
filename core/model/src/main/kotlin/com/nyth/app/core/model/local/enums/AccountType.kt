package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class AccountType(val type: String) : Parcelable {
    Personal(type = "Personal"),
    Business(type = "Business"),
}