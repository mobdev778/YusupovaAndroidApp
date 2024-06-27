package com.github.mobdev778.yusupova.features.splash.impl

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.github.mobdev778.yusupova.core.designsystem.DesignSystem
import com.github.mobdev778.yusupova.core.designsystem.preview.BooleanPreviewParameterProvider
import com.github.mobdev778.yusupova.core.designsystem.rememberDarkMode
import com.github.mobdev778.yusupova.core.router.api.rememberRouter
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen() {
    val router = rememberRouter()
    LaunchedEffect(Unit) {
        delay(3000L)
        router?.back()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DesignSystem.Colors.Background.primary),
        contentAlignment = Alignment.Center
    ) {
        val darkMode = rememberDarkMode()
        val inspectionMode = LocalInspectionMode.current
        val state = remember {
            MutableTransitionState(inspectionMode).apply {
                targetState = true
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visibleState = state,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.Center
            ) {
                val drawableId = when {
                    darkMode -> R.drawable.splash_dark
                    else -> R.drawable.splash_light
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = drawableId),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    DesignSystem.AnimatedText.Title.Medium(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.splash_title),
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif,
                        color = DesignSystem.Colors.Text.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(48.dp))
                    DesignSystem.Text.Title.Small(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.splash_subtitle),
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Serif,
                        color = DesignSystem.Colors.Text.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview(
    @PreviewParameter(BooleanPreviewParameterProvider::class) isDarkTheme: Boolean
) {
    DesignSystem.Theme(isDarkTheme = isDarkTheme) {
        SplashScreen()
    }
}
