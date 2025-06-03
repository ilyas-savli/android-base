package com.nyth.app.core.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.theme.customColorsPalette

@Composable
fun StarRatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = MaterialTheme.customColorsPalette.yellow
) {
    var isHalfStar = (rating % 1) != 0.0
    Row(modifier = modifier) {
        for (index in 1..stars) {
            val image = when {
                index <= rating -> Icons.Rounded.Star
                isHalfStar -> {
                    isHalfStar = false
                    Icons.AutoMirrored.Rounded.StarHalf
                }

                else -> null
            }
            if (image != null) {
                Icon(
                    imageVector = image,
                    contentDescription = null,
                    tint = if (rating == 0.0) MaterialTheme.customColorsPalette.secondary300 else starsColor,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_custom_star),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewComponent() {
    StarRatingBar(
        rating = 2.1,
    )
}