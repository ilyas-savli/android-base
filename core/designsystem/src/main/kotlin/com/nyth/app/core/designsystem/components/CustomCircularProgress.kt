package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nyth.app.core.designsystem.theme.LocalColorsPalette

@Composable
fun CustomCircularProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    fullColor: Color = LocalColorsPalette.current.green400,
    trackColor: Color = LocalColorsPalette.current.green400,
    completedColor: Color = LocalColorsPalette.current.gray100,
    unCompletedColor: Color = LocalColorsPalette.current.black26,
) {
    if (progress != null) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = modifier,
            color = if (progress == 1f) completedColor
            else fullColor,
            trackColor = if (progress == 0f) unCompletedColor
            else trackColor
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier, color = fullColor, trackColor = trackColor
        )
    }
}

@Preview
@Composable
private fun PreviewComponent() {
    Column {
        CustomCircularProgress(
            progress = 0.5f,
            trackColor = LocalColorsPalette.current.disabled,
            fullColor = LocalColorsPalette.current.inputHintColor
        )
        CustomCircularProgress()
        CustomCircularProgress(progress = 0.5f)
        CustomCircularProgress(progress = 0.25f)
        CustomCircularProgress(progress = 0f)
    }
}