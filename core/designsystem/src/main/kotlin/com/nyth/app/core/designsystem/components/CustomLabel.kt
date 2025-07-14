package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyth.app.core.designsystem.ext.safeClickable

@Composable
fun CustomLabel(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent,
    textStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val alpha = if (enabled) 1f else 0.5f
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(50))
            .border(1.dp, borderColor, shape = RoundedCornerShape(50))
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .alpha(alpha)
            .then(
                if (enabled) Modifier.safeClickable { onClick() } else Modifier
            )
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// Large Primary Button Preview
@Preview(name = "LargePrimaryButtonPreview", showBackground = true)
@Preview(
    name = "LargePrimaryButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LargePrimaryButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(16.dp),
        text = "Label",
        backgroundColor = Color(0xFFDC0005),
        borderColor = Color.Transparent,
        enabled = true,
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
    )
}

// Large Primary Button Disabled Preview
@Preview(name = "LargePrimaryButtonDisabledPreview", showBackground = true)
@Preview(
    name = "LargePrimaryButtonDisabledPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LargePrimaryButtonDisabledPreview() {
    CustomLabel(
        modifier = Modifier.padding(16.dp),
        text = "Label",
        backgroundColor = Color(0xFFDC0005).copy(alpha = 0.5f),
        borderColor = Color.Transparent,
        enabled = false,
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
    )
}

// Large Secondary Button Preview
@Preview(name = "LargeSecondaryButtonPreview", showBackground = true)
@Preview(
    name = "LargeSecondaryButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LargeSecondaryButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(16.dp),
        text = "Label",
        backgroundColor = Color.White,
        borderColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDC0005)
        ),
        enabled = true
    )
}

// Large Tertiary Button Preview
@Preview(name = "LargeTertiaryButtonPreview", showBackground = true)
@Preview(
    name = "LargeTertiaryButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LargeTertiaryButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(16.dp),
        text = "Label",
        backgroundColor = Color.Black,
        borderColor = Color.Transparent,
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White),
        enabled = true
    )
}

// Large Outlined Button Preview
@Preview(name = "LargeOutlinedButtonPreview", showBackground = true)
@Preview(
    name = "LargeOutlinedButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LargeOutlinedButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(16.dp),
        text = "Label",
        backgroundColor = Color.Transparent,
        borderColor = Color.Black,
        enabled = true,
        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    )
}

// Medium Primary Button Preview
@Preview(name = "MediumPrimaryButtonPreview", showBackground = true)
@Preview(
    name = "MediumPrimaryButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MediumPrimaryButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(8.dp),
        text = "Label",
        backgroundColor = Color(0xFFDC0005),
        borderColor = Color.Transparent,
        enabled = true,
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
    )
}

// Medium Primary Button Disabled Preview
@Preview(name = "MediumPrimaryButtonDisabledPreview", showBackground = true)
@Preview(
    name = "MediumPrimaryButtonDisabledPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MediumPrimaryButtonDisabledPreview() {
    CustomLabel(
        modifier = Modifier.padding(8.dp),
        text = "Label",
        backgroundColor = Color(0xFFDC0005).copy(alpha = 0.5f),
        borderColor = Color.Transparent,
        enabled = false,
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
    )
}

// Medium Secondary Button Preview
@Preview(name = "MediumSecondaryButtonPreview", showBackground = true)
@Preview(
    name = "MediumSecondaryButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MediumSecondaryButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(8.dp),
        text = "Label",
        backgroundColor = Color.White,
        borderColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDC0005)
        ),
        enabled = true
    )
}

// Medium Tertiary Button Preview
@Preview(name = "MediumTertiaryButtonPreview", showBackground = true)
@Preview(
    name = "MediumTertiaryButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MediumTertiaryButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(8.dp),
        text = "Label",
        backgroundColor = Color.Black,
        borderColor = Color.Transparent,
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White),
        enabled = true
    )
}

// Medium Outlined Button Preview
@Preview(name = "MediumOutlinedButtonPreview", showBackground = true)
@Preview(
    name = "MediumOutlinedButtonPreviewDark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MediumOutlinedButtonPreview() {
    CustomLabel(
        modifier = Modifier.padding(8.dp),
        text = "Label",
        backgroundColor = Color.Transparent,
        borderColor = Color.Black,
        enabled = true,
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    )
} 