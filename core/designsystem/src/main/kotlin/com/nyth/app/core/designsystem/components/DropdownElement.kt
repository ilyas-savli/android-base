package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import com.nyth.app.core.model.remote.response.MetadataFormResponse

@Composable
fun DropDownElementMenu(
    modifier: Modifier = Modifier,
    selectedValueStyle: TextStyle? = null,
    placeHolderValue: String = String.empty,
    placeHolderTextStyle: TextStyle? = typographyNunito.regularNeutral500S14H24,
    menuList: List<MetadataFormResponse.Option>? = emptyList(),
    onItemSelected: (MetadataFormResponse.Option) -> Unit = {},
    required: Boolean? = false,
    checkField: MutableState<Boolean>? = mutableStateOf(false),
    preSelectedItem: String = String.empty,
    hasError: MutableState<Boolean> = mutableStateOf(false),
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf(String.empty) }
    var selectedItem by remember { mutableStateOf(preSelectedItem.ifBlank { String.empty }) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val iconTint = if (selectedItem != String.empty) {
        MaterialTheme.customColorsPalette.secondary800
    } else {
        MaterialTheme.customColorsPalette.secondary500
    }
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp

    } else {
        Icons.Filled.KeyboardArrowDown
    }
    val showError = required.safeGet() && checkField?.value.safeGet() && selectedItem.isEmpty()
    val showErrorContainer : @Composable (Boolean) -> Unit = {
        if (it){
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
                    text = stringResource(id = R.string.required_field),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.error
                    )
                )
            }
        }

    }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clickable(enabled = enabled) { if (enabled) expanded = true }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(4.dp),
                    color = if (enabled) MaterialTheme.customColorsPalette.secondary300 else MaterialTheme.customColorsPalette.secondary100
                )
                .padding(horizontal = 16.dp, vertical = (19.5).dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f),
                text = if (selectedItem == String.empty) placeHolderValue else selectedItem,
                style = if (selectedItem == String.empty) placeHolderTextStyle
                    ?: TextStyle() else selectedValueStyle ?: TextStyle(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Icon(icon,
                contentDescription = String.empty,
                modifier = Modifier
                    .clickable(enabled = enabled) { if (enabled) expanded = !expanded }
                    .weight(0.1f),
                tint = iconTint)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color = MaterialTheme.customColorsPalette.secondary50)
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            menuList?.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.label ?: String.empty) },
                    onClick = {
                        onItemSelected(option)
                        selectedItem = option.label ?: String.empty
                        expanded = false
                    }
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.customColorsPalette.secondary300)
            }
        }
        if (selectedItem.isEmpty()) {
            errorMessage.value = stringResource(id = R.string.required_field)
            hasError.value = true
        } else {
            errorMessage.value = String.empty
            hasError.value = false
        }

        showErrorContainer(showError)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDropDownElementMenu() {
    Column {
        DropDownElementMenu(placeHolderValue = "Seçiniz", required = true, checkField = remember {
            mutableStateOf(true)
        })
    }
}