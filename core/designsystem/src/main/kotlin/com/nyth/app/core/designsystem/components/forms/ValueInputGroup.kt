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
fun ValueInputGroup(
    limit: String,
    stop: String,
    onLimitChange: (String) -> Unit,
    onStopChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    limitUnit: String = "TRY",
    stopUnit: String = "BTC",
    isError: Boolean = false,
    errorText: String = "Bu alan boş bırakılamaz"
) {
    val shape = RoundedCornerShape(12.dp)
    val borderColor = if (isError) Color(0xFFDC0005) else Color(0xFFE5E5E5)
    val backgroundColor = Color.White
    val textColor = Color.Black
    val labelColor = Color(0xFFBDBDBD)
    val errorColor = Color(0xFFDC0005)

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor, shape)
                .border(1.dp, borderColor, shape)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Limit",
                    color = labelColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = limit,
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = limitUnit,
                        color = labelColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Stop",
                    color = labelColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stop,
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stopUnit,
                        color = labelColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
        if (isError) {
            Text(
                text = errorText,
                color = errorColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValueInputGroupDefaultPreview() {
    ValueInputGroup(limit = "70.00", stop = "0.00", onLimitChange = {}, onStopChange = {}, isError = false)
}

@Preview(showBackground = true)
@Composable
fun ValueInputGroupErrorPreview() {
    ValueInputGroup(limit = "70.00", stop = "0.00", onLimitChange = {}, onStopChange = {}, isError = true)
} 