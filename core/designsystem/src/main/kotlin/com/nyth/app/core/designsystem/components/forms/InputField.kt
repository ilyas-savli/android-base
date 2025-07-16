package com.nyth.app.core.designsystem.components.forms

import android.content.res.Configuration
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
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
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
import com.nyth.app.core.designsystem.theme.LocalColorsPalette

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
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
    val palette = LocalColorsPalette.current
    val borderColor = when {
        isError -> palette.error
        isActive -> palette.primaryRed
        else -> palette.divider
    }
    val backgroundColor = if (!enabled) palette.grayBackground else palette.white
    val textColor = if (!enabled) palette.grayTertiary else palette.primaryBlack
    val labelColor = if (isError) palette.error else palette.grayTertiary
    val placeholderColor = palette.grayTertiary
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
                    placeholder = { Text(placeholder, color = placeholderColor) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff, // Replace with dropdown icon
                            contentDescription = "Dropdown",
                            tint = placeholderColor
                        )
                    },
                    singleLine = true,
                    shape = shape,
                    isError = isError,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    textStyle = TextStyle.Default.copy(color = textColor),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        disabledBorderColor = borderColor,
                        errorBorderColor = palette.error,
                        focusedContainerColor = backgroundColor,
                        unfocusedContainerColor = backgroundColor,
                        disabledContainerColor = backgroundColor,
                        errorContainerColor = backgroundColor,
                        cursorColor = textColor,
                        errorCursorColor = palette.error
                    )
                )
            } else {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor, shape)
                        .border(1.dp, borderColor, shape),
                    enabled = enabled,
                    placeholder = { Text(placeholder, color = placeholderColor) },
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
                                    tint = placeholderColor,
                                    modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                                )
                            }
                        }
                        else -> null
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions.Default,
                    textStyle = TextStyle.Default.copy(color = textColor),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        disabledBorderColor = borderColor,
                        errorBorderColor = palette.error,
                        focusedContainerColor = backgroundColor,
                        unfocusedContainerColor = backgroundColor,
                        disabledContainerColor = backgroundColor,
                        errorContainerColor = backgroundColor,
                        cursorColor = textColor,
                        errorCursorColor = palette.error
                    )
                )
            }
        }
        if (isError && errorText != null) {
            Text(
                text = errorText,
                color = palette.error,
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldDefaultPreviewDark() {
    InputField(value = "", onValueChange = {}, label = "Ad", placeholder = "", enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldDropdownPreview() {
    InputField(value = "Lütfen Seçin", onValueChange = {}, label = "Ad", isDropdown = true, enabled = true)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldDropdownPreviewDark() {
    InputField(value = "Lütfen Seçin", onValueChange = {}, label = "Ad", isDropdown = true, enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldPasswordPreview() {
    InputField(value = "", onValueChange = {}, label = "Parola", isPassword = true, enabled = true)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldPasswordPreviewDark() {
    InputField(value = "", onValueChange = {}, label = "Parola", isPassword = true, enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldActivePreview() {
    InputField(value = "I", onValueChange = {}, label = "Ad", isActive = true, enabled = true)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldActivePreviewDark() {
    InputField(value = "I", onValueChange = {}, label = "Ad", isActive = true, enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldTextPreview() {
    InputField(value = "Uğur", onValueChange = {}, label = "Ad", enabled = true)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldTextPreviewDark() {
    InputField(value = "Uğur", onValueChange = {}, label = "Ad", enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldErrorPreview() {
    InputField(value = "Lorem Ipsum Dolor", onValueChange = {}, label = "Ad", isError = true, errorText = "Lorem Ipsum Dolor", enabled = true)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldErrorPreviewDark() {
    InputField(value = "Lorem Ipsum Dolor", onValueChange = {}, label = "Ad", isError = true, errorText = "Lorem Ipsum Dolor", enabled = true)
}

@Preview(showBackground = true)
@Composable
fun InputFieldTextAreaPreview() {
    InputField(value = "Lütfen detayı açıklama yazınız", onValueChange = {}, label = "Ad", isTextArea = true, enabled = true)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputFieldTextAreaPreviewDark() {
    InputField(value = "Lütfen detayı açıklama yazınız", onValueChange = {}, label = "Ad", isTextArea = true, enabled = true)
} 