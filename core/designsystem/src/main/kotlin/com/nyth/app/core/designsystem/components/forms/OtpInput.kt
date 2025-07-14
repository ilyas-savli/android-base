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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpInput(
    otp: List<String>,
    otpLength: Int = 6,
    modifier: Modifier = Modifier,
    focusedIndex: Int = -1,
    onOtpChange: ((Int, String) -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until otpLength) {
            val value = otp.getOrNull(i) ?: ""
            OtpInputBox(
                value = value,
                focused = i == focusedIndex,
                filled = value.isNotEmpty(),
                onValueChange = { newValue ->
                    onOtpChange?.invoke(i, newValue)
                }
            )
        }
    }
}

@Composable
private fun OtpInputBox(
    value: String,
    focused: Boolean,
    filled: Boolean,
    onValueChange: (String) -> Unit = {}
) {
    val borderColor = when {
        focused -> Color(0xFFDC0005)
        else -> Color(0xFFE5E5E5)
    }
    val backgroundColor = Color.White
    val textColor = Color.Black
    val placeholderColor = Color(0xFFBDBDBD)
    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(backgroundColor, shape)
            .border(2.dp, borderColor, shape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (value.isEmpty()) "" else value,
            color = if (value.isEmpty()) placeholderColor else textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtpInputDefaultPreview() {
    OtpInput(otp = listOf("", "", "", "", "", ""))
}

@Preview(showBackground = true)
@Composable
fun OtpInputFocusedPreview() {
    OtpInput(otp = listOf("", "", "", "", "", ""), focusedIndex = 2)
}

@Preview(showBackground = true)
@Composable
fun OtpInputFilledPreview() {
    OtpInput(otp = listOf("2", "2", "2", "2", "2", "2"))
} 