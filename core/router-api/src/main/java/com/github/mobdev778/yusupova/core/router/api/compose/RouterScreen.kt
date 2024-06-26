package com.github.mobdev778.yusupova.core.router.api.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.github.mobdev778.yusupova.core.router.api.AnyScreenId
import com.github.mobdev778.yusupova.core.router.api.LocalRouterProvider
import com.github.mobdev778.yusupova.core.router.api.Router
import com.github.mobdev778.yusupova.core.router.api.base.BaseRouter
import com.github.mobdev778.yusupova.core.router.api.base.RouterState
import com.github.mobdev778.yusupova.core.router.api.rememberRouter

typealias RouterId = String

@Composable
fun RootRouterScreen(
    modifier: Modifier = Modifier,
    content: @Composable (Router) -> Unit
) {
    RouterScreen(
        id = RouterScreen.ROOT_ROUTER_ID,
        modifier = modifier,
        content
    )
}

@Composable
fun RouterScreen(
    id: RouterId,
    modifier: Modifier,
    content: @Composable (Router) -> Unit
) {
    val parentRouter = rememberRouter() as? BaseRouter
    val router = BaseRouter.factory.invoke(parentRouter)
    RouterScreen.idRouterMap[id] = router

    CompositionLocalProvider(
        LocalRouterProvider provides router
    ) {
        content.invoke(router)

        when (val routerState = router.stateFlow.collectAsState().value) {
            is RouterState.Start -> {
                /* do nothing */
            }
            is RouterState.Visible -> {
                for (visibleScreen in routerState.visibleScreens) {
                    Box(
                        modifier = modifier
                    ) {
                        visibleScreen.invoke()
                    }
                }
            }
            is RouterState.Exit -> {
                if (routerState.screenId !is AnyScreenId) {
                    parentRouter?.backWith(routerState.result)?.toScreen(routerState.screenId)
                }
            }
        }
    }
}

object RouterScreen {

    const val ROOT_ROUTER_ID: RouterId = "RouterScreen.Root"
    internal val idRouterMap = HashMap<RouterId, Router>()
}
