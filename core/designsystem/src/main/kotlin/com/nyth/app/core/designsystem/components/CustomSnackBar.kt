package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.local.enums.MessageType

@Composable
fun CustomSnackBar(
    message: String? = null,
    messageType: MessageType = MessageType.ERROR
) {
    if (!message.isNullOrEmpty()) {
        var containerColor = MaterialTheme.customColorsPalette.primary200
        var textStyle = typographyNunito.regularErrorText400S14LH20
        var iconResId: Int? = null
        var iconTintColor: Color? = null
        var borderColor: Color = MaterialTheme.customColorsPalette.transparent

        when (messageType) {
            MessageType.ERROR -> {
                containerColor = MaterialTheme.customColorsPalette.primary200
                textStyle = typographyNunito.regularErrorText400S14LH20
                iconResId = R.drawable.ic_alert
                iconTintColor = MaterialTheme.customColorsPalette.error
                borderColor = MaterialTheme.customColorsPalette.primary50
            }

            MessageType.SUCCESS -> {
                containerColor = MaterialTheme.customColorsPalette.error
                textStyle = typographyNunito.mediumWhiteS14L17
            }

            MessageType.INFORMATION -> {
                containerColor = MaterialTheme.customColorsPalette.primary300
            }

            MessageType.OTHER -> {
                containerColor = MaterialTheme.customColorsPalette.primary300
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(
                    1.dp,
                    borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
        ) {
            Snackbar(containerColor = containerColor) {
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    if (iconResId != null && iconTintColor != null) {
                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = stringResource(id = R.string.default_content_description),
                            tint = iconTintColor,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Text(
                        text = message, style = textStyle
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    Column {
        CustomSnackBar(message = "Example Error Message", messageType = MessageType.ERROR)
        Spacer(modifier = Modifier.height(15.dp))
        CustomSnackBar(message = "Example Message", messageType = MessageType.SUCCESS)
        Spacer(modifier = Modifier.height(15.dp))
        CustomSnackBar(message = "Example Message", messageType = MessageType.INFORMATION)
        Spacer(modifier = Modifier.height(15.dp))
        CustomSnackBar(message = "Example Message", messageType = MessageType.OTHER)
    }
}