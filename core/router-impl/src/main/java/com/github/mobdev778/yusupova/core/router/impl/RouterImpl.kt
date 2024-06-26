package com.github.mobdev778.yusupova.core.router.impl

import androidx.compose.runtime.Composable
import com.github.mobdev778.yusupova.core.router.api.AnyScreenId
import com.github.mobdev778.yusupova.core.router.api.ModalScreenId
import com.github.mobdev778.yusupova.core.router.api.Router
import com.github.mobdev778.yusupova.core.router.api.base.BaseRouter
import com.github.mobdev778.yusupova.core.router.api.base.RouterState
import com.github.mobdev778.yusupova.core.router.api.ScreenId
import com.github.mobdev778.yusupova.core.router.impl.nodes.Command
import com.github.mobdev778.yusupova.core.router.impl.nodes.RouterNode
import com.github.mobdev778.yusupova.core.router.impl.nodes.RouterNodeList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.LinkedList

internal class RouterImpl(
    parent: RouterImpl?
) : BaseRouter {

    private val headNode = RouterNode.createHeadNode()
    private val _stateFlow: MutableStateFlow<RouterState> = MutableStateFlow(RouterState.Start)
    override val stateFlow: StateFlow<RouterState> = _stateFlow.asStateFlow()

    private val nodeList = RouterNodeList()

    init {
        nodeList.init(headNode, parent?.nodeList) {
            onChanged(it)
        }
    }

    override fun navigateTo(screenId: ScreenId): Router = apply {
        return navigateWith(args = ScreenId.NoArgs).toScreen(screenId)
    }

    override fun navigateWith(args: ScreenId.Args): Router.NavigationTransaction {
        return NavigationTransactionImpl(this, args)
    }

    override fun back(result: ScreenId.Result): Router = apply {
        return backWith(result = ScreenId.NoResult).toScreen(screenId = AnyScreenId)
    }

    override fun backWith(result: ScreenId.Result): Router.BackTransaction {
        return BackTransactionImpl(this, result)
    }

    private fun onChanged(observable: RouterNodeList) {
        val visibleNodes = LinkedList<@Composable () -> Unit>()

        for (i in observable.size - 1 downTo 1) {
            val node = observable[i]
            val function: @Composable () -> Unit = {
                node.composable(node.args)
            }
            visibleNodes.addFirst(function)
            if (node.screenId !is ModalScreenId) break
        }

        _stateFlow.value = when {
            visibleNodes.isNotEmpty() -> {
                RouterState.Visible(visibleNodes)
            }
            else -> {
                RouterState.Exit(
                    headNode.backScreenId,
                    headNode.result
                )
            }
        }
    }

    private class NavigationTransactionImpl(
        val router: RouterImpl,
        val args: ScreenId.Args
    ): Router.NavigationTransaction {

        var listener: (ScreenId.Result) -> Unit = {}

        override fun withResultListener(listener: (ScreenId.Result) -> Unit): Router.NavigationTransaction = apply {
            this.listener = listener
        }

        override fun toScreen(screenId: ScreenId): Router {
            val composable = ScreenId.composable(screenId)
            if (composable == null) {
                throw IllegalStateException(
                    "Screen $screenId is not registered. " +
                            "You may have forgotten to call the Router.register(ScreenId, ScreenId.Builder)"
                )
            }
            val node = RouterNode(screenId, args, composable)
            node.resultListener = listener
            router.nodeList.addCommand(Command.Forward(node))
            return router
        }
    }

    private class BackTransactionImpl(
        val router: RouterImpl,
        val result: ScreenId.Result
    ) : Router.BackTransaction {

        override fun toScreen(screenId: ScreenId): Router {
            if (screenId !is AnyScreenId) {
                ScreenId.composable(screenId) ?: throw IllegalStateException(
                    "Screen $screenId is not registered. " +
                            "You may have forgotten to call the Router.register(ScreenId, ScreenId.Builder)"
                )
            }
            router.nodeList.addCommand(Command.Back(screenId = screenId, result = result))
            return router
        }
    }
}
