package com.github.mobdev778.yusupova.app.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mobdev778.yusupova.app.di.component.MainComponent
import com.github.mobdev778.yusupova.core.compose.observe
import com.github.mobdev778.yusupova.core.router.api.ScreenId
import com.github.mobdev778.yusupova.core.router.api.compose.RootRouterScreen
import com.github.mobdev778.yusupova.features.splash.api.SplashScreenId
import com.github.mobdev778.yusupova.app.presentation.theme.YusupovaTheme
import com.github.mobdev778.yusupova.core.router.api.Router
import com.github.mobdev778.yusupova.features.main.api.MainScreenId

class MainActivity : ComponentActivity() {

    private val viewModelFactory by lazy {
        MainComponent.factory().create(application).viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YusupovaTheme {
                val viewModel = remember {
                    viewModelFactory.create()
                }
                RootRouterScreen { router ->
                    viewModel.commands.observe { command ->
                        when (command) {
                            MainCommand.LaunchSplashScreen -> {
                                router
                                    .navigateWith(ScreenId.NoArgs)
                                    .withResultListener {
                                        viewModel.onEvent(MainUiEvent.OnSplashCompleted)
                                    }
                                    .toScreen(SplashScreenId)
                            }
                            MainCommand.LaunchMainScreen -> {
                                router
                                    .navigateWith(ScreenId.NoArgs)
                                    .withResultListener {
                                        viewModel.onEvent(MainUiEvent.OnMainCompleted)
                                    }
                                    .toScreen(MainScreenId)
                            }
                            MainCommand.ExitApplication -> {
                                finish()
                            }
                        }
                    }
                    LaunchedEffect(Unit) {
                        viewModel.onEvent(MainUiEvent.OnStarted)
                    }
                }
            }
        }
    }
}

