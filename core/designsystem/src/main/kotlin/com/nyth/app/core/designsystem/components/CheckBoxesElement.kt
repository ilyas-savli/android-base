package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.theme.LocalColorsPalette

@Composable
fun CustomCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    uncheckedColor: Color? = null,
    uncheckedBoxColor: Color? = null,
    checkedBoxColor: Color? = null,
    checkMarkColor: Color? = null,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    Checkbox(
        modifier = modifier,
        checked = checked,
        onCheckedChange = { isChecked ->
            onCheckedChange(isChecked)
        },
        colors = CheckboxDefaults.colors(
            uncheckedColor = uncheckedColor ?: Color.LightGray,
            checkedColor = checkedBoxColor ?: Color.LightGray,
            checkmarkColor = checkMarkColor ?: Color.LightGray
        ).copy(
            uncheckedBoxColor = uncheckedBoxColor ?: Color.Transparent
        ),
        enabled = enabled
    )
}

@Preview
@Composable
fun CustomCheckBoxPreview() {
    val localColorsPalette = LocalColorsPalette.current

    Column {
        CustomCheckBox(checked = true, onCheckedChange = {})
        CustomCheckBox(
            modifier = Modifier.size(20.dp),
            checked = false,
            onCheckedChange = {},
            uncheckedBoxColor = Color.LightGray,
            checkedBoxColor = localColorsPalette.green50,
            checkMarkColor = localColorsPalette.white
        )
        CustomCheckBox(
            checked = false,
            onCheckedChange = {},
            uncheckedColor = localColorsPalette.black2
        )
        CustomCheckBox(
            checked = true,
            checkMarkColor = Color.Green,
            checkedBoxColor = Color.Red,
            onCheckedChange = {})
    }
}