package com.github.mobdev778.yusupova.core.designsystem.colors

import androidx.compose.ui.graphics.Color

private val dark_cappuccino0 = Color(0xFFBB9D8F)
private val dark_cappuccino1 = Color(0xFF8C8C84)
private val dark_cappuccino2 = Color(0xFF6D4F48)
private val dark_cappuccino3 = Color(0xFF535456)
private val dark_cappuccino4 = Color(0xFF452D27)
private val dark_cappuccino5 = Color(0xFF4B4A4D)
private val dark_cappuccino6 = Color(0xFF333537)
private val dark_cappuccino7 = Color(0xFF151211)

object DarkColors : Colors(
    Background = dsBackground(
        primary = dark_cappuccino7,
        secondary = dark_cappuccino5
    ),
    Text = dsText(
        primary = dark_cappuccino0,
        secondary = dark_cappuccino1,
        link = dark_cappuccino4
    )
)
