package com.nyth.app.core.designsystem.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.components.CustomCheckBox
import com.nyth.app.core.designsystem.theme.customColorsPalette

@Composable
fun ItemAgreementCheck(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    checkBoxPadding: Dp = 16.dp,
    checkedBoxColor: Color? = null,
    checkMarkColor: Color? = null,
    onChecked: () -> Unit
) {
    BaseItemAgreement(
        modifier = modifier,
        isChecked = isChecked,
        checkBoxPadding = checkBoxPadding,
        checkedBoxColor = checkedBoxColor,
        checkMarkColor = checkMarkColor,
        onChecked = onChecked
    )
}

@Composable
private fun BaseItemAgreement(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    checkBoxPadding: Dp = 16.dp,
    checkedBoxColor: Color? = null,
    checkMarkColor: Color? = null,
    onChecked: () -> Unit
) {
    var checked by remember {
        mutableStateOf(isChecked)
    }
    Row(
        modifier = modifier
            .clickable {
                onChecked.invoke()
                checked = !checked
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomCheckBox(
            modifier = Modifier
                .padding(start = checkBoxPadding),
            checked = checked,
            onCheckedChange = {
                onChecked.invoke()
                checked = it
            },
            checkedBoxColor = checkedBoxColor ?: MaterialTheme.customColorsPalette.primary100,
            checkMarkColor = checkMarkColor ?: MaterialTheme.customColorsPalette.white,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewItem() {
    val registerCheckList = listOf(
        "Bireysel Hesap Sözleşmesi ve Eklerini kabul ediyorum.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta ex non nunc faucibus, maximus.",
        "Lorem ipsum dolor sit amet."
    )
    Column {
        repeat(registerCheckList.size) {
            ItemAgreementCheck(
                modifier = Modifier,
                isChecked = false,
                onChecked = {},
            )
        }
    }
}