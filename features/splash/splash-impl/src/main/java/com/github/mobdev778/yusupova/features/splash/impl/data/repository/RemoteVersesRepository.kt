package com.github.mobdev778.yusupova.features.splash.impl.data.repository

import com.github.mobdev778.yusupova.core.domain.verses.BookList

internal interface RemoteVersesRepository {

    suspend fun loadVerses(): BookList
}
