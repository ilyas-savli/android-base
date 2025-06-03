package com.nyth.app.core.designsystem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.ext.ValidatorExt.validateAndShowError
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.ext.StringExt.requiredSymbol
import com.nyth.app.core.model.local.TextFieldState
import com.nyth.app.core.model.local.enums.InputType
import com.nyth.app.core.model.remote.response.MetadataFormResponse

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    onTextImeDone: (String) -> Unit = {},
    inputAreaHeight: Dp? = null,
    state: TextFieldState,
    leftIcon: Painter? = null,
    leftIconTint: Color = MaterialTheme.customColorsPalette.secondary300,
    title: String? = null,
    annotatedTitle: AnnotatedString? = null,
    titleStyle: TextStyle = typographyNunito.mediumNeutral800S14L21,
    placeholder: String? = null,
    inputType: InputType = InputType.TEXT,
    imeAction: ImeAction = ImeAction.Done,
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
    imeBlock: (() -> Unit)? = null,
    validationRules: List<MetadataFormResponse.ValidationRule?>? = null,
    isRequired: Boolean? = true,
    isRequireSymbol: Boolean = false,
    isInTheRow: Boolean = false,
    maxChar: Int? = null,
    enabled: Boolean = true,
    showBorder: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val placeholderLabel: @Composable (() -> Unit)? = placeholder?.let {
        {
            Text(
                text = it,
                style = typographyNunito.regularNeutral500S14H17
            )
        }
    }

    // Keyboard Settings
    val keyboardActions = KeyboardActions(
        onDone = {
            imeBlock?.invoke() ?: focusManager.clearFocus()
            onTextImeDone(state.text.value)
        },
        onNext = {
            imeBlock?.invoke() ?: focusManager.moveFocus(FocusDirection.Down)
        }
    )

    val keyboardType: KeyboardType = when (inputType) {
        InputType.TEXT -> KeyboardType.Text
        InputType.NUMBER -> KeyboardType.Number
        InputType.EMAIL -> KeyboardType.Email
        InputType.PHONE -> KeyboardType.Number
        InputType.CARD_NUMBER -> KeyboardType.Number
        InputType.CARD_EXPIRATION_DATE -> KeyboardType.Number
        InputType.CARD_CVC -> KeyboardType.Number
        InputType.LISTING_PRICE -> KeyboardType.Number
        else -> KeyboardType.Text
    }
    val colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = if (showBorder) MaterialTheme.customColorsPalette.secondary100 else Color.Transparent,
        unfocusedBorderColor = if (showBorder) MaterialTheme.customColorsPalette.secondary200 else Color.Transparent,
        errorBorderColor = if (showBorder) MaterialTheme.customColorsPalette.error else Color.Transparent,
        disabledBorderColor = if (showBorder) MaterialTheme.customColorsPalette.secondary200 else Color.Transparent,
    )

    // Error
    var isError = isError(state)


    // If inputType is PHONE make visual transformation
    val phoneTransform = PhoneNumberTransformation()
    val transform =
        if (inputType == InputType.PHONE)
            phoneTransform
        else if (inputType == InputType.LISTING_NAME)
            NoEmojiTransformation()
        else
            visualTransformation

    if (inputType == InputType.PHONE) {
        state.text.value = phoneTransform.getChangedValue(state.text.value)
    }

    // OnChange method
    val onChanged: (String) -> Unit = { value ->
        val newValue = maxChar?.let {
            if (value.length > it) value.take(it) else value
        } ?: value

        state.text.value = when (inputType) {
            InputType.PHONE -> phoneTransform.getChangedValue(newValue)
            InputType.LISTING_NAME -> newValue.filter { it.isLetter() || it.isDigit() || it == ' ' }
            InputType.NUMBER, InputType.LISTING_PRICE -> newValue.filter { it.isDigit() }
            InputType.ACCOUNT_FIRST_NAME -> newValue.filter { it.isLetter() || it == ' ' }
            InputType.ACCOUNT_LAST_NAME -> newValue.filter { it.isLetter() || it == ' ' }
            InputType.CARD_NAME -> newValue.filter { it.isLetter() || it == ' ' }
            InputType.CARD_NUMBER -> newValue.filter { it.isDigit() }
            InputType.CARD_EXPIRATION_DATE -> newValue.filter { it.isDigit() }
            InputType.CARD_CVC -> newValue.filter { it.isDigit() }
            else -> newValue
        }
        state.error.value = inputType.validateAndShowError(
            context = context,
            stringToValidate = newValue,
            validationRules = validationRules,
            isRequired = isRequired
        )
        isError = isError(state)
    }

    val errorMessage: @Composable () -> Unit = {
        if (state.showError) {
            state.error.value?.let {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_info),
                        tint = MaterialTheme.customColorsPalette.error,
                        contentDescription = String.empty
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = it,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.error
                        )
                    )
                }
            }
        }
    }
    Box(modifier = modifier) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (title != null || annotatedTitle != null) {
                Row {
                    if (annotatedTitle != null) {
                        Text(
                            text = annotatedTitle,
                            style = titleStyle
                        )
                    } else if (title != null) {
                        Text(
                            text = title,
                            style = titleStyle
                        )
                    }
                    if (isRequireSymbol) {
                        Text(
                            text = String.requiredSymbol,
                            style = typographyNunito.regularPrimary100S11H13
                        )
                    }
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .then(if (inputAreaHeight != null) Modifier.height(inputAreaHeight) else Modifier)
                    .fillMaxWidth()
                    .padding(0.dp),
                placeholder = placeholderLabel,
                value = state.text.value,
                isError = isError,
                textStyle = TextStyle(),
                onValueChange = onChanged,
                enabled = enabled,
                keyboardActions = keyboardActions,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                leadingIcon = if (leftIcon != null) {
                    {
                        Icon(
                            painter = leftIcon,
                            contentDescription = String.empty,
                            tint = leftIconTint
                        )
                    }
                } else {
                    null
                },
                visualTransformation = transform,
                singleLine = inputAreaHeight == null,
                trailingIcon = trailingIcon,
                colors = colors
            )
            Box(
                modifier = Modifier
                    .then(if (isInTheRow) Modifier.height(42.dp) else Modifier)
            ) {
                errorMessage()
            }
        }
        if (hideKeyboard) {
            focusManager.clearFocus()
            onFocusClear()
        }
    }
}

fun isError(state: TextFieldState): Boolean {
    state.isError.value = state.showError && state.error.value != null
    return state.showError && state.error.value != null
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun PreviewItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TextInput(
                state = TextFieldState(
                    error = mutableStateOf("Hata mesajı"),
                    showError = true
                ),
                leftIcon = painterResource(id = R.drawable.ic_calendar),
                leftIconTint = MaterialTheme.customColorsPalette.secondary500,
                placeholder = stringResource(id = R.string.month_year),
                maxChar = 4,
                inputType = InputType.CARD_EXPIRATION_DATE,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            TextInput(
                state = TextFieldState(),
                leftIcon = painterResource(id = R.drawable.ic_lock_open),
                leftIconTint = MaterialTheme.customColorsPalette.secondary500,
                placeholder = stringResource(id = R.string.cvc),
                inputType = InputType.CARD_CVC,
                maxChar = 3
            )
        }
    }
}
