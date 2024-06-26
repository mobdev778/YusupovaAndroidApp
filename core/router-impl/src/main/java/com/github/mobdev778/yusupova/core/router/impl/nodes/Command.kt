package com.github.mobdev778.yusupova.core.router.impl.nodes

import com.github.mobdev778.yusupova.core.router.api.ScreenId
import com.github.mobdev778.yusupova.core.router.api.AnyScreenId

internal sealed class Command {
    class Forward(
        val node: RouterNode
    ) : Command()

    class Back(
        val screenId: ScreenId = AnyScreenId,
        val result: ScreenId.Result = ScreenId.NoResult
    ) : Command()

    class Update : Command() {
        var isExpired: Boolean = false
    }
}
