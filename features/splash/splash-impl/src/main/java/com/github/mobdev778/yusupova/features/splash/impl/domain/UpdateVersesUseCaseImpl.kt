package com.github.mobdev778.yusupova.features.splash.impl.domain

import com.github.mobdev778.yusupova.features.splash.impl.data.repository.RemoteVersesRepository
import javax.inject.Inject

internal class UpdateVersesUseCaseImpl @Inject constructor(
    private val remoteVersesRepository: RemoteVersesRepository
): UpdateVersesUseCase {

    override suspend fun invoke() {
        val verses = remoteVersesRepository.loadVerses()
    }
}
