package com.github.mobdev778.yusupova.app.startup

import android.app.Application
import android.content.Context
import com.github.mobdev778.yusupova.app.di.component.AppComponent
import com.github.mobdev778.yusupova.core.network.di.NetworkComponent
import com.github.mobdev778.yusupova.features.BaseFeatureInitializer
import com.github.mobdev778.yusupova.features.dependencies.DependenciesRegistry
import com.github.mobdev778.yusupova.features.main.api.MainDependencies
import com.github.mobdev778.yusupova.features.splash.api.SplashDependencies

class MainAppInitializer : BaseFeatureInitializer<AppComponent>(),
    SplashDependencies,
    MainDependencies
{

    lateinit var appComponent: AppComponent
    override val networkComponent: NetworkComponent by lazy {
        appComponent.networkComponent
    }

    override fun create(context: Context): AppComponent {
        appComponent = AppComponent.factory().create(context.applicationContext as Application)
        DependenciesRegistry.addDependency(SplashDependencies::class, appComponent)
        return appComponent
    }

    companion object {
        init {
            register(MainAppInitializer::class.java)
        }
    }
}
