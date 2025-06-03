package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.theme.LocalCustomColorsPalette

@Composable
fun OTPTextField(
	value: String,
	length: Int,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
	keyboardActions: KeyboardActions = KeyboardActions(),
	onValueChange: (String) -> Unit,
) {
	val spaceBetweenBoxes = 4.dp
    val focusRequester = remember { FocusRequester() }
	BasicTextField(
		modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
		value = value,
		singleLine = true,
		onValueChange = {
			if (it.length <= length) {
				onValueChange(it)
			}
		},
		enabled = enabled,
		keyboardOptions = keyboardOptions,
		keyboardActions = keyboardActions,
		decorationBox = {
			Row(
				Modifier
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(spaceBetweenBoxes),
			) {
				repeat(length) { index ->
					val color =
						if (index == value.length) LocalCustomColorsPalette.current.primary100 else LocalCustomColorsPalette.current.secondary200
					Box(
						modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .weight(1f)
                            .border(
                                1.dp,
                                color = color,
                                shape = RoundedCornerShape(4.dp)
                            ),
						contentAlignment = Alignment.Center
					) {
						Text(
							text = value.getOrNull(index)?.toString() ?: "",
							textAlign = TextAlign.Center,
							style = MaterialTheme.typography.bodyLarge,
							color = color
						)
					}
				}
			}
		})

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun PreviewComponent() {
	OTPTextField(
		modifier = Modifier
			.padding(horizontal = 24.dp),
		value = "1234",
		length = 6
	) {
	}
}