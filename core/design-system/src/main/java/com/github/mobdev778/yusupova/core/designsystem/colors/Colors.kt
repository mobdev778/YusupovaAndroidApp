package com.github.mobdev778.yusupova.core.designsystem.colors

import androidx.compose.ui.graphics.Color

open class Colors(
    val Background: gBackground,
    val Text: gText,
) {
    data class gBackground(
        val primary: Color,
        val secondary: Color
    )
    data class gText(
        val primary: Color,
        val secondary: Color,
        val link: Color
    )
}
