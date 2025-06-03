package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.ext.StringExt.empty

@Composable
fun <T> DropDownInput(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = typographyNunito.mediumNeutral800S14L21,
    selectedValueStyle: TextStyle = TextStyle(),
    placeHolderValue: String = String.empty,
    placeHolderTextStyle: TextStyle = typographyNunito.regularNeutral500S14H24,
    menuList: List<T>? = emptyList(),
    selectedOption: MutableState<T>,
    required: Boolean? = false,
    checkField: MutableState<Boolean>? = mutableStateOf(false),
    hasError: MutableState<Boolean> = mutableStateOf(false),
    text: @Composable (T?) -> String? = { null },
    enabled: Boolean = true,
    onItemSelected: (T) -> Unit = {},
) {
    val expanded = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf(String.empty) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val iconTint = if (selectedOption.value != null) {
        MaterialTheme.customColorsPalette.secondary800
    } else {
        MaterialTheme.customColorsPalette.secondary500
    }
    val icon = if (expanded.value) {
        Icons.Filled.KeyboardArrowUp

    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (title != null) {
            Row {
                Text(
                    text = title,
                    style = titleStyle
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .clickable(enabled = enabled) { if (enabled) expanded.value = true }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(4.dp),
                    color = if (enabled) MaterialTheme.customColorsPalette.secondary300 else MaterialTheme.customColorsPalette.secondary100
                )
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            (if (selectedOption.value != null && selectedOption.value != String.empty) {
                text(selectedOption.value)
            } else {
                placeHolderValue
            })?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f),
                    text = it,
                    style = selectedOption.value?.let { selectedValueStyle }
                        ?: placeHolderTextStyle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Icon(
                icon,
                contentDescription = String.empty,
                modifier = Modifier
                    .weight(0.1f),
                tint = iconTint
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .background(color = MaterialTheme.customColorsPalette.secondary50)
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            menuList?.forEach { option ->
                DropdownMenuItem(
                    text = { text(option)?.let { Text(it) } },
                    onClick = {
                        onItemSelected(option)
                        selectedOption.value = option
                        expanded.value = false
                    }
                )
            }
        }
        if (selectedOption.value == null) {
            errorMessage.value = stringResource(id = R.string.required_field)
            hasError.value = true
        } else {
            errorMessage.value = String.empty
            hasError.value = false
        }

        if (required.safeGet() && checkField?.value.safeGet() && selectedOption.value == null) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_info),
                    tint = MaterialTheme.customColorsPalette.error,
                    contentDescription = String.empty
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = errorMessage.value,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.error
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewScreen() {
    Column {
        DropDownInput(
            placeHolderValue = "Seçiniz",
            required = true,
            selectedOption = remember {
                mutableStateOf(String.empty)
            },
            checkField = remember {
                mutableStateOf(true)
            }) {
        }
    }
}