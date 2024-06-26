package com.github.mobdev778.yusupova.core.router.impl.nodes

import androidx.compose.runtime.Composable
import com.github.mobdev778.yusupova.core.router.api.AnyScreenId
import com.github.mobdev778.yusupova.core.router.api.ScreenId

internal class RouterNode(
    val screenId: ScreenId,
    val args: ScreenId.Args,
    val composable: @Composable (ScreenId.Args) -> Unit
) {
    var backScreenId: ScreenId = AnyScreenId
    var result: ScreenId.Result = ScreenId.NoResult
    var resultListener: (ScreenId.Result) -> Unit = {}

    companion object {

        fun createHeadNode(): RouterNode {
            return RouterNode(
                screenId = object : ScreenId {},
                args = ScreenId.NoArgs,
                composable = {},
            )
        }
    }
}
