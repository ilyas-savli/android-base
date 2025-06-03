package com.nyth.app.core.model.local

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nyth.app.core.model.ext.StringExt.empty

data class TextFieldState(
    val text: MutableState<String> = mutableStateOf(String.empty),
    val error: MutableState<String?> = mutableStateOf(null),
    var showError: Boolean = false,
    val isError: MutableState<Boolean> = mutableStateOf(false),
    val isValueSet: MutableState<Boolean> = mutableStateOf(false)
)