package com.nyth.app.core.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StablexTheme(
    content: @Composable () -> Unit
) {
    val defaultDensity = LocalDensity.current
    val fixedDensity = remember { Density(density = defaultDensity.density, fontScale = 1f) }

    CompositionLocalProvider(
        LocalColorsPalette provides BasePalette(), // our custom palette,
        LocalDensity provides fixedDensity, LocalRippleConfiguration provides null
    ) {
        MaterialTheme(
            colorScheme = BasicColorScheme, typography = DefaultTypography, content = content
        )
    }
}

val BasicColorScheme: ColorScheme = lightColorScheme(
    primary = Color.White,
    onPrimary = Color.Black, // Set a visible color for contrast
    primaryContainer = Color.White,
    onPrimaryContainer = Color.Black,

    secondary = Color.White,
    onSecondary = Color.Black,
    secondaryContainer = Color.White,
    onSecondaryContainer = Color.Black,

    tertiary = Color.White,
    onTertiary = Color.Black,
    tertiaryContainer = Color.White,
    onTertiaryContainer = Color.Black,

    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.Black,

    surfaceVariant = Color.White,
    onSurfaceVariant = Color.Black
)
