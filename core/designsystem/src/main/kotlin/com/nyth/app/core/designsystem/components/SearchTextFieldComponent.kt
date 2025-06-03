package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.theme.customColorsPalette

@Composable
fun SearchTextFieldComponent(
    modifier: Modifier = Modifier,
    state: MutableState<String>,
    placeHolder: String? = null,
    placeHolderTextStyle: TextStyle? = TextStyle(),
    onValueChange: ((String) -> Unit)? = null,
    textStyle: TextStyle? = TextStyle(),
    onDone: (() -> Unit)? = null,
    shape: RoundedCornerShape? = null,
    hideKeyboard: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    onFocusClear: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester(),
    enabled: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.customColorsPalette.white,
        unfocusedContainerColor = MaterialTheme.customColorsPalette.white
    )
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = state.value,
        onValueChange = { value ->
            val newValue = value.filter { it.isLetter() || it.isDigit() || it == ' ' }
            state.value = newValue
            onValueChange?.invoke(newValue)
        },
        modifier
            .then(
                if (shape != null) Modifier.border(
                    2.dp,
                    MaterialTheme.customColorsPalette.secondary200,
                    shape
                ) else Modifier.border(
                    2.dp,
                    MaterialTheme.customColorsPalette.secondary200,
                    RoundedCornerShape(4.dp)
                )
            )
            .then(if (shape != null) Modifier.clip(shape) else Modifier.clip(RoundedCornerShape(4.dp)))
            .focusRequester(focusRequester),
        placeholder = {
            if (!placeHolder.isNullOrEmpty()) {
                Text(text = placeHolder, style = placeHolderTextStyle ?: TextStyle())
            }
        },
        colors = colors,
        leadingIcon = leadingIcon,
        maxLines = 1,
        singleLine = true,
        textStyle = textStyle ?: TextStyle(),
        keyboardActions = KeyboardActions(onDone = { onDone?.invoke() }),
        enabled = enabled
    )
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    val textState = remember {
        mutableStateOf("")
    }
    SearchTextFieldComponent(
        state = textState,
        placeHolder = "Search here...",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
}