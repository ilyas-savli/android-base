package com.nyth.app.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.nyth.app.core.designsystem.R

val typographyNunito = TypographyNunito(
    h2Bold = TextStyle(
        fontSize = 40.sp,
        lineHeight = 56.sp,
        fontFamily = FontFamily(Font(R.font.nunito_bold)),
        fontWeight = FontWeight(700),
        color = CustomColorsPalette().textGeneralTextLight,
        textAlign = TextAlign.Center,
    ),
    h4Bold = TextStyle(
        fontSize = 24.sp,
        lineHeight = 33.6.sp,
        fontFamily = FontFamily(Font(R.font.nunito_bold)),
        fontWeight = FontWeight(700),
        color = CustomColorsPalette().textGeneralTextLight,
        textAlign = TextAlign.Center,
    ),
    // TODO Remove Old Ones
    semiboldPrimary100S14L21 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontWeight = FontWeight(600),
        color = CustomColorsPalette().primary100
    ),
    semiboldSecondary300S15L23 = TextStyle(
        fontSize = 15.sp,
        lineHeight = 23.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontWeight = FontWeight(600),
        color = CustomColorsPalette().secondary300
    ),
    semiboldSecondary300S12L17 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontWeight = FontWeight(600),
        color = CustomColorsPalette().secondary300
    ),
    semiboldSecondary100S16L24 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontWeight = FontWeight(600),
        color = CustomColorsPalette().secondary100
    ),
    regularErrorText400S14LH20 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().error
    ),
    regularNeutral500S11H13 = TextStyle(
        fontSize = 11.sp,
        lineHeight = 13.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral500S14H17 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral500S16H20 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral500S14H21 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral500S14H24 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral500S14H14 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 14.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral500S12H30 = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    regularNeutral900S14H17 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary900
    ),
    regularSecondary100S11H13 = TextStyle(
        fontSize = 11.sp,
        lineHeight = 13.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary100
    ),
    regularSecondary300S12H14 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary300
    ),
    regularSecondary300S14H17 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary300
    ),
    regularSecondary300S24H25 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 25.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary300
    ),
    regularPrimary100S11H13 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 13.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(286),
        color = CustomColorsPalette().primary100
    ),
    regularPrimary200S16H24 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().primary200
    ),
    mediumWhiteS14L17 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().white
    ),
    mediumWhiteS14L20 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().white
    ),
    mediumPrimary100S14L20 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().primary100
    ),
    mediumPrimary200S18L27 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(900),
        color = CustomColorsPalette().primary200
    ),
    mediumSecondary100S24L36 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 36.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(700),
        color = CustomColorsPalette().secondary100
    ),
    mediumSecondary300S18L27 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary300
    ),
    mediumSecondary300S14L17 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary300
    ),
    mediumNeutral0S14L17 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary100
    ),
    mediumNeutral800S14L24 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 17.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary800
    ),
    mediumSecondary300S14L20 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary300
    ),
    mediumSecondary300S20L30 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 30.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary300
    ),
    mediumNeutral500S10L12 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(400),
        color = CustomColorsPalette().secondary500
    ),
    mediumNeutral500S14L11_5 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 11.5.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary500
    ),
    mediumNeutral800S14L21 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary800
    ),
    mediumNeutral800S12L15 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 15.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary800
    ),
    mediumSecondary300S14L12 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 12.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        color = CustomColorsPalette().secondary300
    ),
)

@Immutable
class TypographyNunito(
    val h2Bold: TextStyle,
    val h4Bold: TextStyle,
    val regularNeutral500S11H13: TextStyle,
    val regularNeutral500S14H17: TextStyle,
    val regularNeutral500S16H20: TextStyle,
    val regularSecondary100S11H13: TextStyle,
    val regularSecondary300S14H17: TextStyle,
    val regularSecondary300S24H25: TextStyle,
    val regularSecondary300S12H14: TextStyle,
    val regularPrimary100S11H13: TextStyle,
    val regularPrimary200S16H24: TextStyle,
    val regularNeutral500S12H30: TextStyle,
    val regularNeutral500S14H14: TextStyle,
    val regularNeutral500S14H21: TextStyle,
    val regularNeutral500S14H24: TextStyle,
    val regularNeutral900S14H17: TextStyle,
    val mediumWhiteS14L17: TextStyle,
    val mediumWhiteS14L20: TextStyle,
    val mediumSecondary100S24L36: TextStyle,
    val mediumSecondary300S14L20: TextStyle,
    val mediumSecondary300S14L12: TextStyle,
    val mediumSecondary300S14L17: TextStyle,
    val mediumSecondary300S20L30: TextStyle,
    val mediumNeutral0S14L17: TextStyle,
    val mediumSecondary300S18L27: TextStyle,
    val mediumNeutral500S14L11_5: TextStyle,
    val mediumNeutral500S10L12: TextStyle,
    val mediumNeutral800S14L21: TextStyle,
    val mediumNeutral800S14L24: TextStyle,
    val mediumNeutral800S12L15: TextStyle,
    val mediumPrimary100S14L20: TextStyle,
    val mediumPrimary200S18L27: TextStyle,
    val semiboldPrimary100S14L21: TextStyle,
    val semiboldSecondary300S15L23: TextStyle,
    val semiboldSecondary300S12L17: TextStyle,
    val semiboldSecondary100S16L24: TextStyle,
    val regularErrorText400S14LH20: TextStyle,
)

val TypographyMain = Typography(
    //h1
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W700,
        fontSize = 72.sp,
        letterSpacing = 0.sp
    ),
    //h2
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W700,
        fontSize = 48.sp,
        letterSpacing = 1.sp
    ),
    //h3 display/display small
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W700,
        fontSize = 28.sp,
        letterSpacing = 0.sp
    ),
    // header/h4 highlighted
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        letterSpacing = 0.1.sp
    ),
    //h5
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W700,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    //h6
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W700,
        fontSize = 28.sp,
        letterSpacing = 0.sp
    ),
    //N/A
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    //subtitle1
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        letterSpacing = 0.1.sp
    ),
    //subtitle2 display/display label
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        letterSpacing = 0.1.sp
    ),
    //body1
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    //Button text, normal text,...
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    //caption
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    //overline
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight.W600,
        fontSize = 10.sp,
        letterSpacing = 1.sp
    )
)