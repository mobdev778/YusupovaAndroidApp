package com.github.mobdev778.yusupova.features.splash.impl.di

import com.github.mobdev778.yusupova.features.splash.api.SplashDependencies
import com.github.mobdev778.yusupova.features.splash.impl.presentation.SplashViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        SplashModule::class
    ]
)
internal interface SplashComponent {

    val dependencies: SplashDependencies
    val viewModelFactory: SplashViewModel.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance dependencies: SplashDependencies
        ): SplashComponent
    }

    companion object {

        internal fun factory(): SplashComponent.Factory {
            return DaggerSplashComponent.factory()
        }
    }
}
