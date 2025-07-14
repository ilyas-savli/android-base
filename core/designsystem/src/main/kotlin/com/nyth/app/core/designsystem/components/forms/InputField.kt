package com.nyth.app.core.designsystem.components.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    isPassword: Boolean = false,
    isDropdown: Boolean = false,
    isActive: Boolean = false,
    isTextArea: Boolean = false,
    placeholder: String = "",
    enabled: Boolean = true,
    errorText: String? = null,
    onDropdownClick: (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(8.dp)
    val borderColor = when {
        isError -> Color(0xFFDC0005)
        isActive -> Color(0xFFDC0005)
        else -> Color(0xFFE5E5E5)
    }
    val backgroundColor = if (!enabled) Color(0xFFF5F5F5) else Color.White
    val textColor = if (!enabled) Color(0xFFBDBDBD) else Color.Black
    val labelColor = if (isError) Color(0xFFDC0005) else Color(0xFFBDBDBD)
    val singleLine = !isTextArea
    val visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = labelColor,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Box {
            if (isDropdown) {
                OutlinedTextField(
                    value = value,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor, shape)
                        .border(1.dp, borderColor, shape)
                        .clickable(enabled = enabled) { onDropdownClick?.invoke() },
                    enabled = enabled,
                    readOnly = true,
                    placeholder = { Text(placeholder, color = Color(0xFFBDBDBD)) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff, // Replace with dropdown icon
                            contentDescription = "Dropdown",
                            tint = Color(0xFFBDBDBD)
                        )
                    },
                    singleLine = true,
                    shape = shape,
                    isError = isError,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    textStyle = TextStyle.Default,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        disabledBorderColor = borderColor,
                        errorBorderColor = Color(0xFFDC0005),
                        focusedContainerColor = backgroundColor,
                        unfocusedContainerColor = backgroundColor,
                        disabledContainerColor = backgroundColor,
                        errorContainerColor = backgroundColor,
                        cursorColor = textColor,
                        errorCursorColor = Color(0xFFDC0005)
                    )
                )
            } else {
                OutlinedTextField(
                    value = value,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor, shape)
                        .border(1.dp, borderColor, shape),
                    enabled = enabled,
                    placeholder = { Text(placeholder, color = Color(0xFFBDBDBD)) },
                    singleLine = singleLine,
                    shape = shape,
                    isError = isError,
                    visualTransformation = visualTransformation,
                    trailingIcon = when {
                        isPassword -> {
                            {
                                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                                Icon(
                                    imageVector = icon,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    tint = Color(0xFFBDBDBD),
                                    modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                                )
                            }
                        }
                        else -> null
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions.Default,
                    textStyle = TextStyle.Default,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        disabledBorderColor = borderColor,
                        errorBorderColor = Color(0xFFDC0005),
                        focusedContainerColor = backgroundColor,
                        unfocusedContainerColor = backgroundColor,
                        disabledContainerColor = backgroundColor,
                        errorContainerColor = backgroundColor,
                        cursorColor = textColor,
                        errorCursorColor = Color(0xFFDC0005)
                    )
                )
            }
        }
        if (isError && errorText != null) {
            Text(
                text = errorText,
                color = Color(0xFFDC0005),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldDefaultPreview() {
    InputField(value = "", onValueChange = {}, label = "Ad", placeholder = "", enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldDropdownPreview() {
    InputField(value = "Lütfen Seçin", onValueChange = {}, label = "Ad", isDropdown = true, enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldPasswordPreview() {
    InputField(value = "", onValueChange = {}, label = "Parola", isPassword = true, enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldActivePreview() {
    InputField(value = "I", onValueChange = {}, label = "Ad", isActive = true, enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldTextPreview() {
    InputField(value = "Uğur", onValueChange = {}, label = "Ad", enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldErrorPreview() {
    InputField(value = "Lorem Ipsum Dolor", onValueChange = {}, label = "Ad", isError = true, errorText = "Lorem Ipsum Dolor", enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldTextAreaPreview() {
    InputField(value = "Lütfen detayı açıklama yazınız", onValueChange = {}, label = "Ad", isTextArea = true, enabled = true)
} 