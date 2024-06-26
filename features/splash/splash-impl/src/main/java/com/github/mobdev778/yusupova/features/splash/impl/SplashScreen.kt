package com.github.mobdev778.yusupova.features.splash.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Splash",
            color = Color.Yellow,
            textAlign = TextAlign.Center
        )
    }
}
