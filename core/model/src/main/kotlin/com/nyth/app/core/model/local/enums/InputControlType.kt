package com.nyth.app.core.model.local.enums

enum class InputControlType(val type: String) {
    NumberInput(type = "NumberInput"),
    Dropdown(type = "Dropdown"),
    DateTime(type = "DateTime");

    companion object {
        fun fromType(type: String?): InputControlType =
            values().find { it.type == type } ?: NumberInput
    }
}