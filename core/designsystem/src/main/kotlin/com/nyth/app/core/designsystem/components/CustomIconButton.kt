package com.nyth.app.core.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    icon: Painter = painterResource(id = R.drawable.ic_arrow_up),
    contentDescription: String = "Send"
) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFED2024), Color(0xFF9B1C1F))
    )
    Box(
        modifier = modifier
            .size(48.dp)
            .background(gradient, shape = CircleShape)
            .clickable(enabled = enabled, onClick = onClick), contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription, tint = Color.White, modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(name = "CustomIconButtonPreview", showBackground = true)
@Preview(
    name = "CustomIconButtonPreviewDark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CustomIconButtonPreview() {
    CustomIconButton()
}

@Preview(name = "CustomIconButtonPreviewWithDownIcon", showBackground = true)
@Composable
fun CustomIconButtonPreviewWithDownIcon() {
    CustomIconButton(icon = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "Arrow Down")
}