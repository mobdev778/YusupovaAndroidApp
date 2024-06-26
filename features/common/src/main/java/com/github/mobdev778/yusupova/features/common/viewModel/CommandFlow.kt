@file:OptIn(ExperimentalCoroutinesApi::class)

package com.github.mobdev778.yusupova.features.common.viewModel

import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.receiveAsFlow

@Stable
class CommandFlow<T>(scope: CoroutineScope) : AbstractFlow<T>() {

    internal val channel = Channel<T>(1024)

    init {
        scope.coroutineContext[Job]?.invokeOnCompletion {
            channel.cancel()
        }
    }

    override suspend fun collectSafely(collector: FlowCollector<T>) {
        collector.emitAll(channel.receiveAsFlow())
    }
}

infix fun <T> CommandFlow<T>.emit(value: T) {
    channel.trySend(value)
}


