package com.github.mobdev778.yusupova.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
inline fun <reified T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
    noinline action: suspend (T) -> Unit
) {
    val flow = this
    LaunchedEffect(Unit) {
        flow
            .onEach(action)
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .launchIn(this)
    }
}
