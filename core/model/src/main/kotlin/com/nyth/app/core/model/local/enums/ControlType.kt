package com.nyth.app.core.model.local.enums

enum class ControlType(val type: String) {
    Dropdown(type = "Dropdown"),
    TextInput(type = "TextInput"),
    NumberInput(type = "NumberInput"),
    DateTime(type = "DateTime"),
    CheckboxGroup(type = "CheckboxGroup")
}