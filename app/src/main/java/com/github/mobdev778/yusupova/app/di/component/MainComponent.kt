package com.github.mobdev778.yusupova.app.di.component

import android.app.Application
import com.github.mobdev778.yusupova.app.di.module.MainModule
import com.github.mobdev778.yusupova.app.presentation.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class,
    ],
)
internal interface MainComponent {

    val viewModelFactory: MainViewModel.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): MainComponent
    }

    companion object {

        internal fun factory(): MainComponent.Factory {
            return DaggerMainComponent.factory()
        }
    }
}
