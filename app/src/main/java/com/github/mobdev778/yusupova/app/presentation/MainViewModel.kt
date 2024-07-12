package com.github.mobdev778.yusupova.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.mobdev778.yusupova.features.common.viewModel.CommandFlow
import com.github.mobdev778.yusupova.features.common.viewModel.emit
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainViewModel @AssistedInject constructor() : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelProvider.Factory {

        fun create(): MainViewModel
    }

    internal val commands = CommandFlow<MainCommand>(viewModelScope)

    internal fun onEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.OnStarted -> {
                commands emit MainCommand.LaunchSplashScreen
            }
            MainUiEvent.OnSplashCompleted -> {
                commands emit MainCommand.LaunchMainScreen
            }
            MainUiEvent.OnMainCompleted -> {
                commands emit MainCommand.ExitApplication
            }
        }
    }
}
