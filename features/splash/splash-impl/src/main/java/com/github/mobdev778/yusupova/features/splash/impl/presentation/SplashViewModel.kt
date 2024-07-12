package com.github.mobdev778.yusupova.features.splash.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.mobdev778.yusupova.features.splash.impl.domain.UpdateVersesUseCase
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class SplashViewModel @AssistedInject constructor(
    private val updateVersesUseCase: UpdateVersesUseCase
) : ViewModel() {

    private val _loadingFlow = MutableStateFlow(true)
    val loadingFlow = _loadingFlow.asStateFlow()

    init {
        updateVerses()
    }

    fun updateVerses() {
        viewModelScope.launch {
            updateVersesUseCase.invoke()
            _loadingFlow.value = false
        }
    }

    @AssistedFactory
    interface Factory : ViewModelProvider.Factory {

        fun create(): SplashViewModel
    }
}
