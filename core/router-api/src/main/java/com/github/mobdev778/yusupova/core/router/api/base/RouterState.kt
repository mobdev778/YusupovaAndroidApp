package com.github.mobdev778.yusupova.core.router.api.base

import androidx.compose.runtime.Composable
import com.github.mobdev778.yusupova.core.router.api.ScreenId

interface RouterState {

    data object Start : RouterState

    data class Visible(
        val visibleScreens: List<@Composable () -> Unit>
    ) : RouterState

    data class Exit(
        val screenId: ScreenId,
        val result: ScreenId.Result
    ) : RouterState
}
