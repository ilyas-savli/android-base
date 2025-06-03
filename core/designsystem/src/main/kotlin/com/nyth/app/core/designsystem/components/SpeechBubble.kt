package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.components.base.shape.BubbleShape
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito

@Composable
fun SpeechBubble(
    text: String,
    arrowPosition: ArrowPosition,
    bgColor: Color = MaterialTheme.customColorsPalette.white,
    tintColor: Color = MaterialTheme.customColorsPalette.error,
) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .border(1.dp, tintColor, shape = BubbleShape(arrowPosition = arrowPosition))
            .background(bgColor, shape = BubbleShape(arrowPosition = arrowPosition))
    ) {
        // Add your content here
        Box(modifier = Modifier.padding(16.dp)) {
            Text(
                text = text,
                style = typographyNunito.h4Bold,
            )
        }
    }
    /*Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp).border(border = BorderStroke(1.dp, tintColor),shape = BubbleShape(arrowPosition = arrowPosition)),
        shape = BubbleShape(arrowPosition = arrowPosition),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardColors(
            contentColor = bgColor,
            containerColor = bgColor,
            disabledContainerColor = bgColor,
            disabledContentColor = bgColor
        )
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(
                text = text,
                style = typographyNunito.h4Bold,
            )
        }
    }*/
}

enum class ArrowPosition {
    TOP_LEFT, TOP_CENTER, TOP_RIGHT,
    BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT,
    LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM,
    RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM
}

@Preview
@Composable
private fun PreviewComponent() {
    SpeechBubble(
        text = "Hello, World!",
        arrowPosition = ArrowPosition.BOTTOM_RIGHT
    )
}
