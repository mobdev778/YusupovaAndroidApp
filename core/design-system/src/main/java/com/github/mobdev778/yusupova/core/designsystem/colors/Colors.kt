package com.github.mobdev778.yusupova.core.designsystem.colors

import androidx.compose.ui.graphics.Color

open class Colors(
    val Background: dsBackground,
    val Text: dsText,
) {
    data class dsBackground(
        val primary: Color,
        val secondary: Color
    )
    data class dsText(
        val primary: Color,
        val secondary: Color,
        val link: Color
    )
}
