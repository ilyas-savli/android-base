package com.nyth.app.core.model.local.enums

enum class GrantType(val type: String) {
    Password(type = "password"),
    RefreshToken(type = "refresh_token")
}