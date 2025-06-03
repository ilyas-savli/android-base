package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nyth.app.core.designsystem.theme.customColorsPalette

@Composable
fun CustomCircularProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    fullColor: Color = MaterialTheme.customColorsPalette.primary400,
    trackColor: Color = MaterialTheme.customColorsPalette.primary100,
    completedColor: Color = MaterialTheme.customColorsPalette.success,
    unCompletedColor: Color = MaterialTheme.customColorsPalette.primary100,
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
            trackColor = MaterialTheme.customColorsPalette.primary600,
            fullColor = MaterialTheme.customColorsPalette.primary100
        )
        CustomCircularProgress()
        CustomCircularProgress(progress = 0.5f)
        CustomCircularProgress(progress = 0.25f)
        CustomCircularProgress(progress = 0f)
    }
}