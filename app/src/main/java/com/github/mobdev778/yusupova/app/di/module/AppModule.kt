package com.github.mobdev778.yusupova.app.di.module

import com.github.mobdev778.yusupova.core.domain.AppLocale
import com.github.mobdev778.yusupova.core.domain.ServerAddress
import com.github.mobdev778.yusupova.core.network.di.NetworkComponent
import dagger.Module
import dagger.Provides

@Module
internal interface AppModule {

    companion object {

        @Provides
        fun provideAppLocale(): AppLocale {
            return AppLocale.RU
        }

        @Provides
        fun provideServerAddress(): ServerAddress {
            return ServerAddress(
                "https://raw.githubusercontent.com/mobdev778/yusupova-server/master/"
            )
        }

        @Provides
        fun provideNetworkComponent(
            appLocale: AppLocale,
            serverAddress: ServerAddress
        ): NetworkComponent {
            return NetworkComponent.factory().create(appLocale, serverAddress)
        }
    }
}
