package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.StringExt.empty

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    textStyle: TextStyle? = null,
    onlyIcon: Boolean = false,
    leftIcon: Painter? = null,
    leftIconTint: Color? = null,
    rightIcon: Painter? = null,
    rightIconTint: Color? = null,
    icon: Painter = painterResource(id = R.drawable.ic_arrow_up),
    borderColor: Color = Color.Transparent,
    borderSize: Dp = 3.dp,
    containerColor: Color = MaterialTheme.customColorsPalette.secondary100,
    textColor: Color? = null,
    shape: Shape = (RoundedCornerShape(3.dp)),
    enabled: Boolean = true,
    loadingState: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val loadingArea: @Composable () -> Unit = {
        if (loadingState) {
            CustomCircularProgress(
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 4.dp)
            )
        }
    }
    if (onlyIcon) {
        // TODO: Icon doesn't appear when the button size getting decrease.
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor
            ),
            shape = CircleShape,
            enabled = enabled,
            onClick = { onClick?.invoke() }

        ) {
            Icon(
                painter = icon,
                modifier = Modifier
                    .size(48.dp),
                contentDescription = icon.toString(),
            )
        }
    } else {
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor
            ),
            shape = shape,
            border = BorderStroke(borderSize, borderColor),
            enabled = enabled,
            onClick = { onClick?.invoke() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (leftIcon != null) {
                    Icon(
                        painter = leftIcon,
                        contentDescription = String.empty,
                        tint = leftIconTint ?: Color.Unspecified
                    )
                }
                text?.let {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            text = text,
                            textAlign = TextAlign.Center,
                            style = textStyle ?: TextStyle(),
                            color = textColor ?: Color.Unspecified
                        )
                        loadingArea()
                    }

                }
                if (rightIcon != null) {
                    Icon(
                        painter = rightIcon,
                        modifier = Modifier,
                        contentDescription = rightIcon.toString(),
                        tint = rightIconTint ?: Color.Unspecified
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ButtonPreview() {
    Column {
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = "Button",
            containerColor = Color.Red,
            loadingState = true
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomButton(
            text = "Button",
            rightIcon = painterResource(id = R.drawable.ic_arrow_up)
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomButton(
            text = "Button",
            leftIcon = painterResource(id = R.drawable.ic_arrow_down),
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomButton(
            text = "Button",
            rightIcon = painterResource(id = R.drawable.ic_arrow_up),
            leftIcon = painterResource(id = R.drawable.ic_arrow_down),
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomButton(
            text = "Button",
            onlyIcon = false,
            icon = painterResource(id = R.drawable.ic_arrow_down)
        )
        Spacer(modifier = Modifier.width(8.dp))

        CustomButton(
            text = "Gönderi Oluştur",
            containerColor = Color.LightGray,
            rightIcon = painterResource(id = R.drawable.ic_arrow_up),
            rightIconTint = Color.Green,
            leftIcon = painterResource(id = R.drawable.ic_arrow_down),
            leftIconTint = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        CustomButton(
            text = "Button",
            textColor = MaterialTheme.customColorsPalette.secondary300,
            containerColor = Color.LightGray,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = stringResource(id = R.string.create_post),
                textStyle = typographyNunito.mediumSecondary300S14L20,
                containerColor = MaterialTheme.customColorsPalette.white,
                borderColor = MaterialTheme.customColorsPalette.secondary200,
                borderSize = 1.dp,
                leftIcon = painterResource(id = R.drawable.ic_add_white),
                leftIconTint = MaterialTheme.customColorsPalette.secondary300,
                onClick = {}
            )
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = stringResource(id = R.string.you_joined_the_community),
                textStyle = typographyNunito.mediumWhiteS14L17,
                rightIcon = painterResource(id = R.drawable.ic_arrow_down),
                rightIconTint = MaterialTheme.customColorsPalette.white,
                containerColor = MaterialTheme.customColorsPalette.secondary100,
                onClick = {}
            )
        }
    }
}