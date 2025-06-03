package com.nyth.app.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.Secondary100
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.StringExt.empty

@Composable
fun ListItemRow(
    text: String,
    @DrawableRes icon: Int? = null,
    navigationIconVisible: Boolean = false,
    textAlign: TextAlign? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.customColorsPalette.white)
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = Secondary100,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            }
            .padding(top = 20.dp, bottom = 20.dp, start = 16.dp, end = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        icon?.let { icon ->
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = icon),
                contentDescription = String.empty,
                tint = MaterialTheme.customColorsPalette.secondary100
            )
        }
        Text(
            modifier = Modifier
                .weight(1f),
            text = text,
            style = typographyNunito.regularSecondary300S14H17,
            textAlign = textAlign ?: TextAlign.Start,
        )
        if (navigationIconVisible) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_arrow_forward_ios),
                contentDescription = String.empty,
                tint = MaterialTheme.customColorsPalette.secondary300
            )
        }
    }
}