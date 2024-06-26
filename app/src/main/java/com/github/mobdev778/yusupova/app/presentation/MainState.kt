package com.github.mobdev778.yusupova.app.presentation

sealed interface MainState {
    data object Start : MainState
    data object Splash : MainState
    data object Main : MainState
}
