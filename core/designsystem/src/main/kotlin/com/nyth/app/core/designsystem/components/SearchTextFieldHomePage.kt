package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.model.ext.StringExt.empty

@Composable
fun SearchTextFieldHomePage(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    placeHolder: String? = null,
    placeHolderTextStyle: TextStyle? = TextStyle(),
    onValueChange: (() -> Unit)? = null,
    textStyle: TextStyle? = TextStyle(),
    onDone: (() -> Unit)? = null,
    hideKeyboard: Boolean = false,
    onClick: (() -> Unit)? = null,
    enableField: Boolean = true
) {
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.clickable { onClick?.invoke() }) {
        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
                onValueChange?.invoke()
            },
            modifier
                .border(2.dp, MaterialTheme.customColorsPalette.white, RoundedCornerShape(86.dp))
                .clip(RoundedCornerShape(86.dp)),
            placeholder = {
                if (!placeHolder.isNullOrEmpty()) {
                    Text(text = placeHolder, style = placeHolderTextStyle ?: TextStyle())
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.customColorsPalette.secondary100,
                unfocusedContainerColor = MaterialTheme.customColorsPalette.secondary100
            ),
            maxLines = 1,
            singleLine = true,
            textStyle = textStyle ?: TextStyle(),
            keyboardActions = KeyboardActions(onDone = { onDone?.invoke() }
            ),
            enabled = enableField
        )
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)
                .align(Alignment.CenterEnd)
                .background(
                    color = MaterialTheme.customColorsPalette.primary200,
                    shape = CircleShape
                )
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = String.empty,
                tint = MaterialTheme.customColorsPalette.white
            )
        }
    }
    if (hideKeyboard) {
        focusManager.clearFocus()
    }
}

@Preview
@Composable
private fun PreviewComponent() {
    val textState = remember {
        mutableStateOf(TextFieldValue(String.empty))
    }
    SearchTextFieldHomePage(
        state = textState,
        placeHolder = "Search here...",
        modifier = Modifier.fillMaxWidth()
    )
}