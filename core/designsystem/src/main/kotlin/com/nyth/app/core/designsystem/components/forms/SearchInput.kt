package com.nyth.app.core.designsystem.components.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Piyasalarda ara",
    isFocused: Boolean = false,
    enabled: Boolean = true
) {
    val shape = RoundedCornerShape(50)
    val borderColor = if (isFocused) Color(0xFFDC0005) else Color(0xFFE5E5E5)
    val backgroundColor = Color.White
    val textColor = Color.Black
    val placeholderColor = Color(0xFFBDBDBD)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, shape)
            .border(1.dp, borderColor, shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search.",
            tint = placeholderColor,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = placeholderColor,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            } else {
                Text(
                    text = value,
                    color = textColor,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchInputPlaceholderPreview() {
    SearchInput(value = "", onValueChange = {}, isFocused = false)
}

@Preview(showBackground = true)
@Composable
fun SearchInputFocusedPreview() {
    SearchInput(value = "I", onValueChange = {}, isFocused = true)
}

@Preview(showBackground = true)
@Composable
fun SearchInputFocusedTextPreview() {
    SearchInput(value = "EtHe", onValueChange = {}, isFocused = true)
} 