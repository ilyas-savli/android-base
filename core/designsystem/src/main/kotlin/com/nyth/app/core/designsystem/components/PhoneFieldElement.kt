package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.local.enums.InputType

@Composable
fun PhoneFieldElement(
    modifier: Modifier = Modifier,
    required: Boolean = false,
    fieldTitle: String? = null,
    inputValue: MutableState<String> = mutableStateOf(String.empty),
    placeHolderValue: String = String.empty,
    errorMsg: MutableState<String?> = mutableStateOf(null),
    successMsg: MutableState<String?> = mutableStateOf(null),
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
    oldPhoneNumber: MutableState<String> = mutableStateOf(String.empty),
    hasPhoneError: MutableState<Boolean> = mutableStateOf(false)
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (fieldTitle != null) {
            Row {
                Text(text = fieldTitle)
                if (required) {
                    Text(text = stringResource(id = R.string.require))
                }
            }
        }
        Box {
            PhoneMaskField(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth(),
                areaContext = context,
                areaFocusManager = focusManager,
                areaFocusRequester = focusRequester,
                placeHolderText = placeHolderValue,
                placeHolderTextStyle = typographyNunito.regularNeutral500S14H17,
                borderColor = MaterialTheme.customColorsPalette.secondary300,
                inputValue = inputValue,
                hasError = hasPhoneError,
                inputType = InputType.PHONE,
                singleLine = true,
                onPhoneChanged = { newPhone ->
                    if (newPhone == oldPhoneNumber.value) {
                        hasPhoneError.value = true
                    }
                },
                hideKeyboard = hideKeyboard,
                onFocusClear = {
                    onFocusClear.invoke()
                },
            )
        }
        successMsg.value?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info),
                    tint = MaterialTheme.customColorsPalette.success,
                    contentDescription = String.empty
                )
                Text(
                    text = it,
                    color = MaterialTheme.customColorsPalette.success
                )
            }
        }
        errorMsg.value?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_info),
                    tint = MaterialTheme.customColorsPalette.error,
                    contentDescription = String.empty
                )
                Text(
                    text = it, style = TextStyle(
                        color = MaterialTheme.customColorsPalette.error
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPhoneField() {
    Column {
        PhoneFieldElement(
            inputValue = remember { mutableStateOf("5455454545") },
            placeHolderValue = "d",
            fieldTitle = "Lorem Ipsum",
            required = true,
            successMsg = remember { mutableStateOf("Updated!") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PhoneFieldElement(
            inputValue = remember { mutableStateOf("5455454545") },
            placeHolderValue = "d",
            fieldTitle = "Lorem Ipsum",
            errorMsg = remember { mutableStateOf("Update Error!") },
            required = true,
        )
    }
}