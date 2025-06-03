package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.nyth.app.core.model.local.enums.MessageType
import com.nyth.app.core.model.local.enums.UiState

@Composable
fun DefaultSnackBar(
    snackbarHostState: SnackbarHostState,
    message: String? = null,
    uiState: UiState? = null,
    messageType: MessageType? = null,
    paddingValues: PaddingValues? = null
) {
    val type = messageType ?: if (uiState == UiState.SUCCESS)
        MessageType.SUCCESS
    else
        MessageType.ERROR

    if (!message.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .padding(top = paddingValues?.calculateTopPadding() ?: 0.dp)
        ) {
            SnackbarHost(
                modifier = Modifier.align(Alignment.TopCenter),
                hostState = snackbarHostState
            ) {
                Popup(
                    alignment = Alignment.TopCenter,
                    onDismissRequest = {
                        it.dismiss()
                    }
                ) {
                    CustomSnackBar(message = message, messageType = type)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    Column {
        CustomSnackBar(message = "Error Message", messageType = MessageType.ERROR)
        Spacer(modifier = Modifier.height(15.dp))
        CustomSnackBar(message = "Example Message", messageType = MessageType.SUCCESS)
        Spacer(modifier = Modifier.height(15.dp))
        CustomSnackBar(message = "Example Message", messageType = MessageType.INFORMATION)
        Spacer(modifier = Modifier.height(15.dp))
        CustomSnackBar(message = "Example Message", messageType = MessageType.OTHER)
    }
}