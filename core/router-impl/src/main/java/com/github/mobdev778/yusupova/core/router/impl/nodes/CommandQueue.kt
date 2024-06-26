package com.github.mobdev778.yusupova.core.router.impl.nodes

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class CommandQueue(private val handler: (Command) -> Unit) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val commandFlow = MutableSharedFlow<Command>(replay = Int.MAX_VALUE)

    private var pendingUpdate: Command.Update? = null

    init {
        scope.launch {
            commandFlow.collect { command ->
                when {
                    command is Command.Update && command.isExpired -> Unit
                    else -> handler.invoke(command)
                }
            }
        }
    }

    fun addCommand(command: Command) {
        if (command is Command.Update) {
            pendingUpdate?.isExpired = true
            pendingUpdate = command
        }
        commandFlow.tryEmit(command)
    }

    fun close() {
        scope.cancel()
    }
}
