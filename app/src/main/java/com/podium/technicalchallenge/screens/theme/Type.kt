package com.podium.technicalchallenge.screens.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.podium.technicalchallenge.R

private val Montserrat = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    h1 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 34.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 36.sp
    ),
    h2 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp
    ),
    h3 = TextStyle(
        fontFamily = Montserrat,
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp
    ),
)