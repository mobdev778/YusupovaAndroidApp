package com.github.mobdev778.yusupova.core.router.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

interface Router {

    fun navigateTo(screenId: ScreenId): Router

    fun navigateWith(args: ScreenId.Args): NavigationTransaction

    fun back(result: ScreenId.Result = ScreenId.NoResult): Router

    fun backWith(result: ScreenId.Result): BackTransaction

    interface BackTransaction {

        fun toScreen(screenId: ScreenId): Router
    }

    interface NavigationTransaction {

        fun withResultListener(listener: (ScreenId.Result) -> Unit): NavigationTransaction

        fun toScreen(screenId: ScreenId): Router
    }
}

val LocalRouterProvider = compositionLocalOf<Router?> { null }

@Composable
fun rememberRouter(): Router? {
    return LocalRouterProvider.current
}

