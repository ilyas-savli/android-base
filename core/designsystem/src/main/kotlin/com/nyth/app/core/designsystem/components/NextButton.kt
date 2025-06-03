package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito

@Composable
fun NextButton(
	modifier: Modifier = Modifier,
	title: String = stringResource(id = R.string.continue_button),
	enabled: Boolean = true,
	isEnabled: (() -> Boolean)? = null,
	onClick: () -> Unit = {}
) {
	Box(
		modifier = modifier
            .shadow(elevation = 16.dp, shape = RoundedCornerShape(0.dp))
            .fillMaxWidth()
            .background(color = MaterialTheme.customColorsPalette.white)
	) {
		CustomButton(
			modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
			text = title,
			textStyle = typographyNunito.mediumWhiteS14L17,
			containerColor = MaterialTheme.customColorsPalette.primary100,
			enabled = isEnabled?.invoke() ?: enabled,
			onClick = {
				onClick.invoke()
			})
	}
}