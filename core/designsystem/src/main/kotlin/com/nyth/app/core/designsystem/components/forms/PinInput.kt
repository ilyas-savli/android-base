package com.nyth.app.core.designsystem.components.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PinInput(
    pin: List<String>,
    pinLength: Int = 6,
    modifier: Modifier = Modifier,
    filledColor: Color = Color(0xFFDC0005),
    emptyColor: Color = Color(0xFFE5E5E5),
    textColor: Color = Color.Black,
    onPinChange: ((Int, String) -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pinLength) {
            val value = pin.getOrNull(i) ?: ""
            PinInputCircle(
                value = value,
                filled = value.isNotEmpty(),
                filledColor = filledColor,
                emptyColor = emptyColor,
                textColor = textColor,
                onValueChange = { newValue ->
                    onPinChange?.invoke(i, newValue)
                }
            )
        }
    }
}

@Composable
private fun PinInputCircle(
    value: String,
    filled: Boolean,
    filledColor: Color,
    emptyColor: Color,
    textColor: Color,
    onValueChange: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(
                color = if (filled) filledColor else Color.Transparent,
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = if (filled) filledColor else emptyColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (filled) {
            Text(
                text = value,
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinInputDefaultPreview() {
    PinInput(pin = listOf("", "", "", "", "", ""))
}

@Preview(showBackground = true)
@Composable
fun PinInputFilledPreview() {
    PinInput(pin = listOf("2", "2", "2", "2", "2", "2"))
} 