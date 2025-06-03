package com.nyth.app.core.model.local.enums

enum class ContentHeight(val ratio: Float? = null) {
    WRAP,
    FIXED(ratio = (175f.div(205)))
}