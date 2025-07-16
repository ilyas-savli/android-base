package com.nyth.app.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColorsPalette = staticCompositionLocalOf { BasePalette() }

open class BasePalette {
    // Primary Colors
    val primaryRed: Color = Color(0xFFDC0005) // AkYatırım Red
    val primaryBlack: Color = Color(0xFF141414) // Stablex Black
    val primaryWhite: Color = Color(0xFFFFFFFF) // AkYatırım White

    // Secondary Colors
    val secondaryGreen: Color = Color(0xFF59B167) // Green
    val secondaryOrange: Color = Color(0xFFF66A47) // Orange
    val secondaryBlack: Color = Color(0xFF1D1D1E) // Black Secondary
    val tertiaryBlack: Color = Color(0xFF3A3A3A) // Black Tertiary

    // Grays
    val grayPrimary: Color = Color(0xFF4E4E4E) // Gray Primary
    val graySecondary: Color = Color(0xFF6D6D6D) // Gray Secondary
    val grayTertiary: Color = Color(0xFFBABABA) // Gray Tertiary
    val grayFourth: Color = Color(0xFFE4E4E4) // Gray Fourth
    val grayBackground: Color = Color(0xFFF5F5F5) // Background

    // Gradients (as solid fallback)
    val gradientBlack: Color = Color(0xFF191919) // Used in gradients
    val gradientGray: Color = Color(0xFF131313) // Used in gradients

    // Surface/Backgrounds
    val surfaceDark: Color = Color(0xFF1D1D1E)
    val surfaceLight: Color = Color(0xFFFFFFFF)
    val surfaceGray: Color = Color(0xFFF5F5F5)

    // Text Colors
    val textPrimary: Color = Color(0xFF22242A)
    val textSecondary: Color = Color(0xFF4E4E4E)
    val textTertiary: Color = Color(0xFF6D6D6D)
    val textOnPrimary: Color = Color(0xFFFFFFFF)
    val textOnSecondary: Color = Color(0xFF141414)
    val textDisabled: Color = Color(0xFF605F65)
    val textHint: Color = Color(0x8A000000)

    // Border/Divider
    val borderDefault: Color = Color(0xFFE4E4E4)
    val borderDark: Color = Color(0xFF3A3A3A)
    val borderLight: Color = Color(0xFFFFFFFF)
    val divider: Color = Color(0xFFE5E5E5)

    // State/Feedback
    val error: Color = Color(0xFFEA6669)
    val warning: Color = Color(0xFFF66A47)
    val success: Color = Color(0xFF59B167)
    val info: Color = Color(0xFF5D5864)
    val disabled: Color = Color(0xFF605F65)
    val disabledBackground: Color = Color(0xFFBABABA)

    // Misc
    val green50: Color = Color(0xFFF2F8F2)
    val green400: Color = Color(0xFF2AB0A1)
    val gray100: Color = Color(0xFFE4E4E4)
    val black2: Color = Color(0xFF000000).copy(alpha = 0.3f)
    val greenLive: Color = Color(0xFF2CCC00)
    val blackDefault: Color = Color(0xFF22242A) // overlinePrimaryLight
    val pdpVideoButton: Color = blackDefault // 0xFF22242A
    val searchBarVerticalDividerColor: Color = Color(0xFFE5E5E5)
    val white: Color = Color(0xFFFFFFFF)
    val black26: Color = Color(0x42000000)
    val inputHintColor = Color(0x8A000000)
}