package com.github.mobdev778.yusupova.app.presentation

internal sealed interface MainUiEvent {
    data object OnStarted : MainUiEvent
    data object OnSplashCompleted : MainUiEvent
    data object OnMainCompleted : MainUiEvent
}
