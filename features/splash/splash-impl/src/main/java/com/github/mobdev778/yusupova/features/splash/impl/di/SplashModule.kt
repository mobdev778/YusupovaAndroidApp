package com.github.mobdev778.yusupova.features.splash.impl.di

import com.github.mobdev778.yusupova.features.splash.impl.domain.UpdateVersesUseCase
import com.github.mobdev778.yusupova.features.splash.impl.domain.UpdateVersesUseCaseImpl
import com.github.mobdev778.yusupova.features.splash.impl.repository.RemoteVersesRepository
import com.github.mobdev778.yusupova.features.splash.impl.repository.RemoteVersesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface SplashModule {

    @Binds
    fun bindUpdateVersesUseCase(useCase: UpdateVersesUseCaseImpl): UpdateVersesUseCase

    @Binds
    fun bindRemoteVersesRepository(repository: RemoteVersesRepositoryImpl): RemoteVersesRepository
}
