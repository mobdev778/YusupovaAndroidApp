package com.github.mobdev778.yusupova.app.di.component

import android.app.Application
import com.github.mobdev778.yusupova.app.di.module.AppModule
import com.github.mobdev778.yusupova.app.presentation.MainViewModel
import com.github.mobdev778.yusupova.core.network.di.NetworkComponent
import com.github.mobdev778.yusupova.features.splash.api.SplashDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
    ],
)
interface AppComponent : SplashDependencies {

    override val networkComponent: NetworkComponent
    val viewModelFactory: MainViewModel.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }

    companion object {

        internal fun factory(): AppComponent.Factory {
            return DaggerAppComponent.factory()
        }
    }
}
