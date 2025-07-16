package com.nyth.app.core.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyth.app.core.designsystem.theme.LocalColorsPalette
import com.nyth.app.core.designsystem.R

@Composable
fun ImageLabel(
    text: String,
    modifier: Modifier = Modifier,
    leftIcon: Painter? = null,
    rightIcon: Painter? = null,
    color: Color = LocalColorsPalette.current.primaryRed,
    backgroundColor: Color = LocalColorsPalette.current.surfaceLight,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = color),
    textDecoration: TextDecoration = TextDecoration.None
) {
    val shape = RoundedCornerShape(6.dp)
    Surface(
        modifier = modifier
            .background(backgroundColor, shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = Color.Transparent,
        shape = shape,
        shadowElevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leftIcon != null) {
                Icon(
                    painter = leftIcon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = textStyle.copy(color = color),
                textDecoration = textDecoration
            )
            if (rightIcon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = rightIcon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// Previews
@Preview(name = "ImageLabel Default", showBackground = true)
@Composable
fun PreviewImageLabelDefault() {
    ImageLabel(
        text = "Label",
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceLight
    )
}

@Preview(
    name = "ImageLabel Default Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewImageLabelDefaultDark() {
    ImageLabel(
        text = "Label",
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceDark
    )
}

@Preview(name = "ImageLabel Underline", showBackground = true)
@Composable
fun PreviewImageLabelUnderline() {
    ImageLabel(
        text = "Label",
        textDecoration = TextDecoration.Underline,
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceLight
    )
}

@Preview(
    name = "ImageLabel Underline Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewImageLabelUnderlineDark() {
    ImageLabel(
        text = "Label",
        textDecoration = TextDecoration.Underline,
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceDark
    )
}

@Preview(name = "ImageLabel Left Icon", showBackground = true)
@Composable
fun PreviewImageLabelLeftIcon() {
    ImageLabel(
        text = "Label",
        leftIcon = painterResource(id = R.drawable.ic_add_white),
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceLight
    )
}

@Preview(
    name = "ImageLabel Left Icon Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewImageLabelLeftIconDark() {
    ImageLabel(
        text = "Label",
        leftIcon = painterResource(id = R.drawable.ic_add_white),
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceDark
    )
}

@Preview(name = "ImageLabel Right Icon", showBackground = true)
@Composable
fun PreviewImageLabelRightIcon() {
    ImageLabel(
        text = "Label",
        rightIcon = painterResource(id = R.drawable.ic_add_white),
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceLight
    )
}

@Preview(
    name = "ImageLabel Right Icon Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewImageLabelRightIconDark() {
    ImageLabel(
        text = "Label",
        rightIcon = painterResource(id = R.drawable.ic_add_white),
        color = LocalColorsPalette.current.primaryRed,
        backgroundColor = LocalColorsPalette.current.surfaceDark
    )
} 