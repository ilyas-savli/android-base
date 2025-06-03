package com.nyth.app.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val darkColorScheme = darkColorScheme(
    primary = Color(0xFF0000FF),
    secondary = Secondary100,
    background = Color(0xFF1C1C1E),
    surface = Color(0xFF1C1B1F),
    onPrimary = Color(0xFF1C1B1F),
    onSecondary = Color(0xFF1C1B1F),
    onTertiary = Color(0xFF252526),
    onBackground = Color(0xFFF2F4F8),
    onSurface = Color(0xFFFFFBFE),
    error = Error,
    onError = Color(0xFF1C1B1F),
    primaryContainer = Color(0xFF252526),
    secondaryContainer = Color(0xFF1C1C1E),
    tertiaryContainer = Color(0xFF3A3A3C),
    errorContainer = Error,
    onPrimaryContainer = Color(0xFFFFFBFE),
    onTertiaryContainer = Color(0x80FFFBFE),
    onSecondaryContainer = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFFFFFBFE),
    outline = Color(0xFCFFFFFF),
    outlineVariant = Color(0xFC000000),
    scrim = Color((0xFFE9E6E3))
)

private val lightColorScheme = lightColorScheme(
    primary = Color(0xFF0000FF),
    background = Color(0xFFF2F4F8),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFBFE),
    onSecondary = Color(0xFFFFFBFE),
    onTertiary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    error = Error,
    onError = Color(0xFFFFFBFE),
    primaryContainer = Color(0xFCFFFFFF),
    secondaryContainer = Color(0xFFF2F4F8),
    errorContainer = Error,
    onPrimaryContainer = Color(0xFF1C1B1F),
    onTertiaryContainer = Color(0x801C1B1F),
    onSecondaryContainer = Color(0xFF000000),
    onErrorContainer = Color(0xFF1C1B1F),
    outline = Color(0xFCFFFFFF),
    outlineVariant = Color(0xFC000000),
    scrim = Color((0xFFBBBBBB))
)

/**
 * Custom Color Palette for Dark and Light Mode
 */
@Immutable
data class CustomColorsPalette(
    val primary900: Color = Primary900,
    val primary800: Color = Primary800,
    val primary700: Color = Primary700,
    val primary600: Color = Primary600,
    val primary500: Color = Primary500,
    val primary400: Color = Primary400,
    val primary300: Color = Primary300,
    val primary200: Color = Primary200,
    val primary100: Color = Primary100,
    val primary50: Color = Primary50,
    val secondary900: Color = Secondary900,
    val secondary800: Color = Secondary800,
    val secondary700: Color = Secondary700,
    val secondary600: Color = Secondary600,
    val secondary500: Color = Secondary500,
    val secondary400: Color = Secondary400,
    val secondary300: Color = Secondary300,
    val secondary200: Color = Secondary200,
    val secondary100: Color = Secondary100,
    val secondary50: Color = Secondary50,
    val infoPrimary: Color = InfoPrimary,
    val info: Color = Info,
    val success: Color = Success,
    val warning: Color = Warning,
    val error: Color = Error,
    val lightDisabled: Color = LightDisabled,
    val darkDisabled: Color = DarkDisabled,
    val buttonDisabled: Color = ButtonDisabled,
    val grey900: Color = Grey900,
    val grey800: Color = Grey800,
    val grey700: Color = Grey700,
    val grey600: Color = Grey600,
    val grey500: Color = Grey500,
    val grey400: Color = Grey400,
    val grey300: Color = Grey300,
    val grey200: Color = Grey200,
    val grey100: Color = Grey100,
    val grey50: Color = Grey50,
    val gradientPrimary: Brush = GradientPrimary,
    val gradientBlue: Brush = GradientBlue,
    val gradientPurple: Brush = GradientPurple,
    val gradientGreen: Brush = GradientGreen,
    val gradientOrange: Brush = GradientOrange,
    val gradientRed: Brush = GradientRed,
    val gradientBrown: Brush = GradientBrown,
    val gradientYellow: Brush = GradientYellow,
    val dark1: Color = Dark1,
    val dark2: Color = Dark2,
    val dark3: Color = Dark3,
    val dark4: Color = Dark4,
    val dark5: Color = Dark5,
    val red: Color = Red,
    val pink: Color = Pink,
    val purple: Color = Purple,
    val deepPurple: Color = DeepPurple,
    val indigo: Color = Indigo,
    val blue: Color = Blue,
    val lightBlue: Color = LightBlue,
    val cyan: Color = Cyan,
    val teal: Color = Teal,
    val green: Color = Green,
    val lightGreen: Color = LightGreen,
    val lime: Color = Lime,
    val yellow: Color = Yellow,
    val amber: Color = Amber,
    val orange: Color = Orange,
    val deepOrange: Color = DeepOrange,
    val brown: Color = Brown,
    val blueGrey: Color = BlueGrey,
    val conditioner: Color = Conditioner,
    val peachOrange: Color = PeachOrange,
    val persianPastel: Color = PersianPastel,
    val paleMauve: Color = PaleMauve,
    val americanPink: Color = AmericanPink,
    val lustyGallant: Color = LustyGallant,
    val himalayanBalsam: Color = HimalayanBalsam,
    val sugarChic: Color = SugarChic,
    val lilas: Color = Lilas,
    val lavenderBlue: Color = LavenderBlue,
    val apocyan: Color = Apocyan,
    val pastelTurquoise: Color = PastelTurquoise,
    val dawnDeparts: Color = DawnDeparts,
    val magicMint: Color = MagicMint,
    val distilledMoss: Color = DistilledMoss,
    val menthol: Color = Menthol,
    val backgroundPrimary: Color = BackgroundPrimary,
    val backgroundBlue: Color = BackgroundBlue,
    val backgroundPurple: Color = BackgroundPurple,
    val backgroundGreen: Color = BackgroundGreen,
    val backgroundOrange: Color = BackgroundOrange,
    val backgroundRed: Color = BackgroundRed,
    val backgroundTeal: Color = BackgroundTeal,
    val backgroundBrown: Color = BackgroundBrown,
    val backgroundYellow: Color = BackgroundYellow,
    val backgroundGrey: Color = BackgroundGrey,
    val transparent: Color = Transparent,
    val transparentPrimary: Color = TransparentPrimary,
    val transparentBlue: Color = TransparentBlue,
    val transparentPurple: Color = TransparentPurple,
    val transparentGreen: Color = TransparentGreen,
    val transparentOrange: Color = TransparentOrange,
    val transparentRed: Color = TransparentRed,
    val transparentTeal: Color = TransparentTeal,
    val transparentBrown: Color = TransparentBrown,
    val transparentYellow: Color = TransparentYellow,
    val transparentGrey: Color = TransparentGrey,
    val white: Color = White,
    val black: Color = Black,
    val textGeneralTextLight: Color = TextGeneralTextLight,
    val bgGreen: Color = Color(0xFF172112),
    val boxGreen: Color = Color(0xFF2E4229),
    val bottomBar: Color = Color(0xFF21301C),
    val bottomBarUnselected: Color = Color(0xFFA3C299),
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(
    primary300 = Primary300,
    primary200 = Primary200,
    primary100 = Primary100,
    primary50 = Primary50,
    secondary300 = Secondary300,
    secondary200 = Secondary200,
    secondary100 = Secondary100,
    secondary50 = Secondary50,
)

val OnDarkCustomColorsPalette = CustomColorsPalette(
    primary300 = Primary300,
    primary200 = Primary200,
    primary100 = Primary100,
    primary50 = Primary50,
    secondary300 = Secondary300,
    secondary200 = Secondary200,
    secondary100 = Secondary100,
    secondary50 = Secondary50,
)

val MaterialTheme.customColorsPalette: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    inline get() = LocalCustomColorsPalette.current

@Composable
fun AppDefaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val customColorsPalette =
        MaterialTheme.customColorsPalette

    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = TypographyMain,
            content = content
        )
    }
}