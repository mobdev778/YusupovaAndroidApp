package com.github.mobdev778.yusupova.features.splash.impl.di

import com.github.mobdev778.yusupova.features.splash.api.SplashDependencies
import com.github.mobdev778.yusupova.features.splash.impl.domain.UpdateVersesUseCase
import com.github.mobdev778.yusupova.features.splash.impl.domain.UpdateVersesUseCaseImpl
import com.github.mobdev778.yusupova.features.splash.impl.data.repository.RemoteVersesRepository
import com.github.mobdev778.yusupova.features.splash.impl.data.repository.RemoteVersesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface SplashModule {

    @Binds
    fun bindUpdateVersesUseCase(useCase: UpdateVersesUseCaseImpl): UpdateVersesUseCase

    companion object {

        @JvmStatic
        @Provides
        fun provideRemoteVersesRepository(
            dependencies: SplashDependencies
        ): RemoteVersesRepository {
            return RemoteVersesRepositoryImpl(dependencies)
        }
    }
}
