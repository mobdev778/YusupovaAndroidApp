package com.github.mobdev778.yusupova.features.splash.impl

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        delay(2000L)
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
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.8f),
                contentAlignment = Alignment.Center
            ) {
                val drawableId = when {
                    darkMode -> R.drawable.splash_dark
                    else -> R.drawable.splash_light
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    painter = painterResource(id = drawableId),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.splash_title),
                        color = DesignSystem.Colors.Text.primary,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.splash_subtitle),
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
