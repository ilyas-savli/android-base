package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.ext.ValidatorExt.validateAndShowError
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.local.enums.InputType
import com.nyth.app.core.model.remote.response.MetadataFormResponse
import timber.log.Timber

@Composable
fun TextFieldElement(
    modifier: Modifier = Modifier,
    inputValue: MutableState<String> = mutableStateOf(String.empty),
    inputValueTextStyle: TextStyle? = null,
    placeHolderValue: String = String.empty,
    placeHolderTextStyle: TextStyle? = typographyNunito.regularNeutral500S14H24,
    rightIcon: Painter? = null,
    rightIconTint: Color = MaterialTheme.customColorsPalette.secondary300,
    leftIcon: Painter? = null,
    leftIconTint: Color = MaterialTheme.customColorsPalette.secondary300,
    requiredText: Boolean = false,
    requiredTextTextStyle: TextStyle? = null,
    fieldTitle: String? = null,
    fieldTitleTextStyle: TextStyle? = null,
    warningMsg: MutableState<String?> = mutableStateOf(null),
    errorMsg: MutableState<String?> = mutableStateOf(null),
    hasError: MutableState<Boolean> = mutableStateOf(false),
    borderColor: Color = Color.LightGray,
    inputType: InputType? = InputType.TEXT,
    validationRules: List<MetadataFormResponse.ValidationRule?>? = null,
    singleLine: Boolean = true,
    hideKeyboard: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onFocusClear: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    maxChar: Int? = null,
    required: Boolean? = false,
    colors: TextFieldColors? = null,
    checkField: MutableState<Boolean>? = mutableStateOf(false)
) {
    val context = LocalContext.current
    val textValue = remember {
        inputValue
    }
    val warning by remember { mutableStateOf(warningMsg.value) }
    val focusManager = LocalFocusManager.current
    if (checkField?.value.safeGet() && required.safeGet() && textValue.value.isEmpty()) {
        hasError.value = true
        errorMsg.value = context.getString(R.string.required_field)
    }
    val errorMessage : @Composable () -> Unit  = {
        if (checkField?.value.safeGet()) {
            errorMsg.value?.let {
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
        Column {
            if (fieldTitle != null) {
                Row(
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(text = fieldTitle, style = fieldTitleTextStyle ?: TextStyle())
                    if (requiredText) {
                        Text(
                            text = stringResource(id = R.string.require),
                            style = requiredTextTextStyle ?: TextStyle()
                        )
                    }
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                placeholder = {
                    Text(
                        text = placeHolderValue, style = placeHolderTextStyle ?: TextStyle()
                    )
                },
                value = textValue.value,
                textStyle = inputValueTextStyle ?: TextStyle(),
                colors = colors ?: OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor, unfocusedBorderColor = borderColor
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                trailingIcon = if (rightIcon != null) {
                    {
                        Icon(
                            painter = rightIcon,
                            contentDescription = String.empty,
                            tint = rightIconTint
                        )
                    }
                } else {
                    null
                },
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
                keyboardOptions = when (inputType) {
                    InputType.PHONE -> KeyboardOptions(keyboardType = KeyboardType.Phone)
                    InputType.NUMBER -> KeyboardOptions(keyboardType = KeyboardType.Number)
                    InputType.EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
                    else -> KeyboardOptions.Default
                },
                visualTransformation = visualTransformation,
                singleLine = singleLine,
                onValueChange = { value ->
                    onValueChange(value)

                    if (maxChar != null) {
                        if (value.length <= maxChar) {
                            when (inputType) {
                                InputType.PHONE -> {
                                    textValue.value = value.filter { it.isDigit() }
                                    inputValue.value = value.filter { it.isDigit() }
                                }

                                InputType.NUMBER -> {
                                    textValue.value = value.filter { it.isDigit() }
                                    inputValue.value = value.filter { it.isDigit() }
                                }

                                else -> {
                                    textValue.value = value
                                    inputValue.value = value
                                }
                            }
                        }
                    } else {
                        when (inputType) {
                            InputType.PHONE -> {
                                textValue.value = value.filter { it.isDigit() }
                                inputValue.value = value.filter { it.isDigit() }
                            }

                            InputType.NUMBER -> {
                                textValue.value = value.filter { it.isDigit() }
                                inputValue.value = value.filter { it.isDigit() }
                            }

                            else -> {
                                textValue.value = value
                                inputValue.value = value
                            }
                        }
                    }
                    Timber.d("TextValueLog -> ${textValue.value}|${inputValue.value}|$value")

                    inputType?.validateAndShowError(
                        context = context,
                        stringToValidate = value,
                        validationRules = validationRules
                    )?.let { validationResult ->
                        hasError.value = true
                        errorMsg.value = validationResult
                    } ?: run {
                        hasError.value = false
                        errorMsg.value = null
                    }
                },
            )
            warning?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = String.empty
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp), text = it
                    )
                }
            }
            errorMessage()
        }
    }

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponents() {
    Column(
        modifier = Modifier
    ) {
        Box {
            TextFieldElement(
                placeHolderValue = "İsim",
            )
        }
        Box {
            TextFieldElement(
                placeHolderValue = "İsim",
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        TextFieldElement(
            placeHolderValue = "Lütfen yazınız..", fieldTitle = stringResource(id = R.string.year)
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldElement(placeHolderValue = "Lütfen yazınız..",
            fieldTitle = stringResource(id = R.string.year),
            warningMsg = remember {
                mutableStateOf("Example Warning")
            })
        Spacer(modifier = Modifier.height(15.dp))
        TextFieldElement(
            placeHolderValue = "Lütfen yazınız..",
            fieldTitle = stringResource(id = R.string.year),
            rightIcon = painterResource(id = R.drawable.ic_remove_red_eye),
            errorMsg = remember {
                mutableStateOf("Example Error")
            },
            leftIcon = painterResource(id = R.drawable.ic_zoom_in)
        )
    }
}