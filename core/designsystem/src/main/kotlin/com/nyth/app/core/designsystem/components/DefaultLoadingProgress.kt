package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.model.local.enums.UiState

@Composable
fun DefaultLoadingProgress(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.customColorsPalette.white),
    uiState: UiState
) {
    if (uiState == UiState.LOADING) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomCircularProgress(modifier = Modifier.size(50.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    DefaultLoadingProgress(uiState = UiState.LOADING)
}