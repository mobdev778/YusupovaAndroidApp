package com.github.mobdev778.yusupova.features.splash.impl.repository

import com.github.mobdev778.yusupova.core.domain.verses.Book
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class RemoteVersesRepositoryImpl @Inject constructor() : RemoteVersesRepository {

    override suspend fun loadVerses(): List<Book> {
        delay(3000L)
        return emptyList()
    }
}
