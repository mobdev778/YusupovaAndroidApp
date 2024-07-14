package com.github.mobdev778.yusupova.features.splash.impl.data.repository

import com.github.mobdev778.yusupova.core.domain.verses.BookList
import com.github.mobdev778.yusupova.features.splash.api.SplashDependencies
import com.github.mobdev778.yusupova.features.splash.impl.data.mapper.BookListDtoMapper
import retrofit2.Retrofit
import javax.inject.Inject

internal class RemoteVersesRepositoryImpl @Inject constructor(
    dependencies: SplashDependencies
) : RemoteVersesRepository {

    val api: VersesApi by lazy {
        val retrofit: Retrofit = dependencies.networkComponent.retrofit
        retrofit.create(VersesApi::class.java)
    }

    val mapper: BookListDtoMapper by lazy {
        BookListDtoMapper()
    }

    override suspend fun loadVerses(): BookList {
        val bookListDto = api.getVerses()
        return mapper.convert(bookListDto)
    }
}
