package com.github.mobdev778.yusupova.app.presentation

internal sealed interface MainCommand {
    data object LaunchSplashScreen : MainCommand
    data object LaunchMainScreen : MainCommand
    data object ExitApplication : MainCommand
}
