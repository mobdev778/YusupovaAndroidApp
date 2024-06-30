package com.github.mobdev778.yusupova.features.common.viewModel

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

@Composable
inline fun <reified VM : ViewModel> viewModel(
    noinline create: (stateHandle: SavedStateHandle) -> VM
) : Lazy<VM> {
    val context = LocalContext.current
    val activity = context.getActivity() ?: throw IllegalStateException("Unable to get activity from context: $context")
    return activity.viewModels {
        Factory(activity, create)
    }
}

class Factory<T: ViewModel>(
    savedStateRegistryOwner: SavedStateRegistryOwner,
    private val create: (stateHandle: SavedStateHandle) -> T
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return create.invoke(handle) as T
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
