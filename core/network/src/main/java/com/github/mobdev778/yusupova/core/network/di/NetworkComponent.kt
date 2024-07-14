package com.github.mobdev778.yusupova.core.network.di

import com.github.mobdev778.yusupova.core.domain.AppLocale
import com.github.mobdev778.yusupova.core.domain.ServerAddress
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
    ],
)
interface NetworkComponent {

    val retrofit: Retrofit

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance appLocale: AppLocale,
            @BindsInstance serverAddress: ServerAddress
        ): NetworkComponent
    }

    companion object {

        fun factory(): Factory {
            return DaggerNetworkComponent.factory()
        }
    }
}
