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
        @JvmStatic
        fun provideAppLocale(): AppLocale {
            return AppLocale.RU
        }

        @Provides
        @JvmStatic
        fun provideServerAddress(): ServerAddress {
            return ServerAddress(
                "https://github.com/mobdev778/yusupova-server/raw/master/"
            )
        }

        @Provides
        @JvmStatic
        fun provideNetworkComponent(
            appLocale: AppLocale,
            serverAddress: ServerAddress
        ): NetworkComponent {
            return NetworkComponent.factory().create(appLocale, serverAddress)
        }
    }
}
