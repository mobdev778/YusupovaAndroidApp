package com.github.mobdev778.yusupova.core.designsystem.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object DefaultTypography : Typography(
    headline1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.Default,
        fontSize = 96.sp,
        letterSpacing = -1.5.sp,
    ),
    headline2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.Default,
        fontSize = 60.sp,
        letterSpacing = -0.5.sp,
    ),
    headline3 = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 48.sp,
        letterSpacing = 0.sp,
    ),
    headline4 = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp,
    ),
    headline5 = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
    ),
    headline6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp,
    ),

    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
    ),

    body1 = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
    ),

    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal, // Regular
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
    ),
)
