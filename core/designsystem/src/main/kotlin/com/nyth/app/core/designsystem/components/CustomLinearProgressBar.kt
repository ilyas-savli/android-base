package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.theme.customColorsPalette

@Composable
fun CustomLinearProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float = 50f,
    itemCount: Int = 3
) {
    val itemRange = 100f / itemCount
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(itemCount) {
            val index = it + 1
            val minItemPercentage = itemRange * it
            val maxItemPercentage = itemRange * index
            val itemPercentage = if (percentage >= maxItemPercentage) {
                1f
            } else {
                if (percentage < minItemPercentage) {
                    0.001f
                } else {
                    (percentage - minItemPercentage) / itemRange
                }
            }

            Box(
                modifier = Modifier
                    .weight(itemPercentage)
                    .height(4.dp)
                    .background(color = MaterialTheme.customColorsPalette.primary100)
            )

            if (itemPercentage != 1f) {
                Box(
                    modifier = Modifier
                        .weight(1f - itemPercentage)
                        .height(4.dp)
                        .background(color = MaterialTheme.customColorsPalette.secondary100)
                )
            }

            if (index != itemCount) {
                Spacer(modifier = Modifier.weight(0.03f))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    CustomLinearProgressBar(modifier = Modifier.fillMaxWidth(), percentage = 50f)
}