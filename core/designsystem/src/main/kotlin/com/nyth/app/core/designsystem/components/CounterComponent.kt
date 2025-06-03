package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CounterComponent(
    modifier : Modifier = Modifier,
    startNumber: Int,
    numberStyle: TextStyle? = null,
    onCountFinished: () -> Unit = {}) {
    var currentNumber by remember { mutableStateOf(startNumber) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(startNumber) {
        coroutineScope.launch {
            for (i in startNumber downTo 0) {
                currentNumber = i
                delay(1000)
            }
            onCountFinished()
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = currentNumber.toString(),
            style = numberStyle ?: TextStyle()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent(){
    CounterComponent(startNumber = 10)
}