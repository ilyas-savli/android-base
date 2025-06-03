package com.nyth.app.core.designsystem.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.ext.ValidatorExt.validate
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.local.enums.InputType
import com.nyth.app.core.model.utils.AppConstants
import com.nyth.app.core.model.utils.AppConstants.Companion.PHONE_COUNTRY_TR_CODE
import com.nyth.app.core.model.utils.AppConstants.Companion.PHONE_PREFIX

@Composable
fun PhoneMaskField(
    modifier: Modifier = Modifier,
    placeHolderText: String = "",
    inputValue: MutableState<String> = mutableStateOf(String.empty),
    inputValueTextStyle: TextStyle? = null,
    placeHolderTextStyle: TextStyle? = null,
    areaContext: Context? = null,
    areaFocusManager: FocusManager? = null,
    areaFocusRequester: FocusRequester? = null,
    mask: String = AppConstants.PHONE_MASK,
    maskNumber: Char = AppConstants.PHONE_MASK_CHAR,
    inputType: InputType? = InputType.PHONE,
    borderColor: Color = MaterialTheme.customColorsPalette.secondary300,
    errorMsg: MutableState<String?> = mutableStateOf(null),
    hasError: MutableState<Boolean> = mutableStateOf(false),
    warningMsg: MutableState<String?> = mutableStateOf(null),
    onPhoneChanged: (String) -> Unit,
    hideKeyboard: Boolean = false,
    singleLine: Boolean = true,
    isRequired: Boolean = true,
    onFocusClear: () -> Unit = {},
) {
    var error by remember { mutableStateOf(errorMsg.value) }
    val context = areaContext ?: LocalContext.current
    val focusManager = areaFocusManager ?: LocalFocusManager.current
    val warning by remember { mutableStateOf(warningMsg.value) }
    val focusRequester = areaFocusRequester ?: remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    inputValue.value = inputValue.value.removePrefix(PHONE_PREFIX)
    if (inputValue.value.length >= 2) {
        inputValue.value =
            if (inputValue.value.startsWith(PHONE_COUNTRY_TR_CODE)) inputValue.value else PHONE_COUNTRY_TR_CODE
    } else if (inputValue.value.length == 1) {
        inputValue.value = PHONE_COUNTRY_TR_CODE.take(1)
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            value = inputValue.value,
            onValueChange = { it ->
                val maskValueCount = mask.count { it == maskNumber || it.isDigit() }
                val filteredValue = it.trim { !it.isDigit() }.take(maskValueCount)
                inputValue.value = filteredValue
                inputType?.validate(stringToValidate = filteredValue, isRequired = isRequired)
                    ?.let { validationResult ->
                        error = if (validationResult == AppConstants.NO_ERROR_VALIDATOR) {
                            hasError.value = false
                            null
                        } else {
                            hasError.value = true
                            context.getString(validationResult)
                        }
                    }
                onPhoneChanged(filteredValue)
            },
            placeholder = {
                Text(text = placeHolderText, style = placeHolderTextStyle ?: TextStyle())
            },
            textStyle = inputValueTextStyle ?: TextStyle(),
            singleLine = singleLine,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor, unfocusedBorderColor = borderColor
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = PhoneVisualTransformation(mask, maskNumber),
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
        error?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_info),
                    tint = MaterialTheme.customColorsPalette.error,
                    contentDescription = String.empty
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp), text = it, style = TextStyle(
                        color = MaterialTheme.customColorsPalette.error
                    )
                )
            }
        }
    }
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    PhoneMaskField(onPhoneChanged = {})
}