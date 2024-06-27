package com.github.mobdev778.yusupova.core.designsystem.colors

import androidx.compose.ui.graphics.Color

private val light_cappuccino0 = Color(0xFF6F4E37)
private val light_cappuccino1 = Color(0xFFA47F66)
private val light_cappuccino2 = Color(0xFFC0997F)
private val light_cappuccino3 = Color(0xFFDCB499)
private val light_cappuccino4 = Color(0xFFF9CFB4)
private val light_cappuccino5 = Color(0xFFFFECD0)
private val light_cappuccino6 = Color(0xFFFFFFEC)
private val light_cappuccino7 = Color(0xFFFFFFFF)

object LightColors : Colors(
    Background = dsBackground(
        primary = light_cappuccino7,
        secondary = light_cappuccino5
    ),
    Text = dsText(
        primary = light_cappuccino1,
        secondary = light_cappuccino4,
        link = light_cappuccino0
    )
)

