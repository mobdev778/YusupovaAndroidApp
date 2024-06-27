package com.github.mobdev778.yusupova.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.github.mobdev778.yusupova.core.designsystem.colors.Colors
import com.github.mobdev778.yusupova.core.designsystem.colors.DarkColors
import com.github.mobdev778.yusupova.core.designsystem.colors.LightColors
import com.github.mobdev778.yusupova.core.designsystem.preview.BooleanPreviewParameterProvider

internal val LocalColors = staticCompositionLocalOf<Colors> { LightColors }
internal val LocalDarkMode = staticCompositionLocalOf<Boolean> { false }

object DesignSystem {

    @Composable
    fun Theme(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        colors: Colors? = null,
        content: @Composable () -> Unit
    ) {
        val themeColors = getThemeColors(colors, isDarkTheme)
        CompositionLocalProvider(
            LocalColors provides themeColors,
            LocalDarkMode provides isDarkTheme
        ) {
            content()
        }
    }

    val Colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}

@Composable
fun rememberDarkMode(): Boolean {
    return LocalDarkMode.current
}

private fun getThemeColors(colors: Colors?, isDarkTheme: Boolean): Colors {
    return when {
        colors != null -> colors
        isDarkTheme -> DarkColors
        else -> LightColors
    }
}

@Preview
@Composable
fun DesignSystemPreview(
    @PreviewParameter(BooleanPreviewParameterProvider::class) isDarkTheme: Boolean
) {
    DesignSystem.Theme(isDarkTheme = isDarkTheme) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DesignSystem.Colors.Background.primary)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(color = DesignSystem.Colors.Background.secondary)
                ) {
                    Text(
                        text = "Toolbar",
                        color = DesignSystem.Colors.Text.primary
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    text = "Primary",
                    color = DesignSystem.Colors.Text.primary
                )
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    text = "Secondary",
                    color = DesignSystem.Colors.Text.secondary
                )
            }
        }
    }
}

