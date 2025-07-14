package com.nyth.app.core.designsystem.ext

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun Modifier.conditional(
    condition: Boolean, modifier: @Composable Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

@Composable
fun Modifier.safeClickable(
    debounceTimeMillis: Long = 500.toLong(), onClick: () -> Unit
): Modifier = composed {
    var lastClickTimeMillis by remember { mutableLongStateOf(0L) }

    clickable {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTimeMillis) >= debounceTimeMillis) {
            onClick()
            lastClickTimeMillis = currentTime
        }
    }
}