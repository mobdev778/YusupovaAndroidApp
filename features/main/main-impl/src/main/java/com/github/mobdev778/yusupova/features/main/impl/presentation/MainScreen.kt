package com.github.mobdev778.yusupova.features.main.impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.github.mobdev778.yusupova.core.designsystem.DesignSystem

@Composable
internal fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        DesignSystem.Text.Title.Small(
            modifier = Modifier.fillMaxWidth(),
            text = "Main",
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = DesignSystem.Colors.Text.primary,
            textAlign = TextAlign.Center
        )
    }
}
