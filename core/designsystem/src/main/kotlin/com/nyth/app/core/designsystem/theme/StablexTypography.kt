package com.nyth.app.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nyth.app.core.designsystem.R

@Stable
val mulishRegular = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal)
)

@Stable
val mulishBold = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

@Stable
val mulishLight = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Light)
)

@Stable
val mulishMedium = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Medium)
)

@Stable
val mulishSemiBold = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.SemiBold)
)

@Stable
val mulishExtraBold = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.ExtraBold)
)


val DefaultTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = mulishRegular
    )
)


object StablexTypography {
    val mulish200 = TextStyle(
        fontFamily = mulishLight,
        fontWeight = FontWeight.ExtraLight,
    )

    val mulish300 = TextStyle(
        fontFamily = mulishLight,
        fontWeight = FontWeight.Light,
    )

    val mulish300Light = TextStyle(
        fontFamily = mulishLight,
        fontWeight = FontWeight.Light,
    )

    val mulish400 = TextStyle(
        fontFamily = mulishRegular,
        fontWeight = FontWeight.Normal,
    )

    val mulish500 = TextStyle(
        fontFamily = mulishMedium,
        fontWeight = FontWeight.Medium,
    )

    val mulish600 = TextStyle(
        fontFamily = mulishSemiBold,
        fontWeight = FontWeight.SemiBold,
    )

    val mulish700 = TextStyle(
        fontFamily = mulishBold,
        fontWeight = FontWeight.Bold,
    )

    val mulish800 = TextStyle(
        fontFamily = mulishBold,
        fontWeight = FontWeight.ExtraBold,
    )
    val mulish900 = TextStyle(
        fontFamily = mulishExtraBold,
        fontWeight = FontWeight.ExtraBold,
    )

}

@Composable
fun getMulish400(): TextStyle {
    val colors = LocalColorsPalette.current
    return StablexTypography.mulish400.copy(
        color = colors.blackDefault, fontSize = 12.sp
    )
}

@Composable
fun getMulish300(): TextStyle {
    val colors = LocalColorsPalette.current
    return StablexTypography.mulish300.copy(
        color = colors.blackDefault, fontSize = 12.sp
    )
}


@Composable
fun getMulish700(): TextStyle {
    val colors = LocalColorsPalette.current
    return StablexTypography.mulish700.copy(
        color = colors.blackDefault, fontSize = 14.sp
    )
}