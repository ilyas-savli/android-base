package com.nyth.app.core.designsystem.components.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember

@Composable
fun DatePickerField(
    label: String = "",
    day: String = "",
    month: String = "",
    year: String = "",
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isPhone: Boolean = false
) {
    val shape = RoundedCornerShape(8.dp)
    val borderColor = Color(0xFF007AFF)
    val backgroundColor = if (!enabled) Color(0xFFF5F5F5) else Color.White
    val textColor = if (!enabled) Color(0xFFBDBDBD) else Color.Black
    val labelColor = Color(0xFFBDBDBD)

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = labelColor,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        if (!isPhone) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor, shape)
                    .border(1.dp, borderColor, shape)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DatePickerInput(
                    value = day,
                    onValueChange = onDayChange,
                    placeholder = "Gün",
                    modifier = Modifier.weight(1f),
                    enabled = enabled
                )
                DatePickerInput(
                    value = month,
                    onValueChange = onMonthChange,
                    placeholder = "Ay",
                    modifier = Modifier.weight(1f),
                    enabled = enabled
                )
                DatePickerInput(
                    value = year,
                    onValueChange = onYearChange,
                    placeholder = "Yıl",
                    modifier = Modifier.weight(1f),
                    enabled = enabled
                )
            }
        }
    }
}

@Composable
private fun DatePickerInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(8.dp))
            .padding(vertical = 12.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (value.isEmpty()) placeholder else value,
            color = if (value.isEmpty()) Color(0xFFBDBDBD) else Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerFieldDefaultPreview() {
    DatePickerField(label = "Doğum tarihi", day = "", month = "", year = "")
}

@Composable
fun PhoneInputField(
    phone: String = "",
    onPhoneChange: (String) -> Unit = {},
    label: String = "Telefon Numarası",
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    InputField(
        value = phone,
        onValueChange = onPhoneChange,
        label = label,
        placeholder = "(___) ___ __ __",
        modifier = modifier,
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
fun PhoneInputFieldPreview() {
    var phone = remember { "" }
    PhoneInputField(phone = phone, onPhoneChange = {})
} 