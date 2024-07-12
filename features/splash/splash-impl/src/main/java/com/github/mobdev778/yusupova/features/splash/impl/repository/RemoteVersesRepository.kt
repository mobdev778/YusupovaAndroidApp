package com.github.mobdev778.yusupova.features.splash.impl.repository

import com.github.mobdev778.yusupova.core.domain.verses.Book

internal interface RemoteVersesRepository {

    suspend fun loadVerses(): List<Book>
}
