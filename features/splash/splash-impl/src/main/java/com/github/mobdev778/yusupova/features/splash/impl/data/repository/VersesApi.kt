package com.github.mobdev778.yusupova.features.splash.impl.data.repository

import com.github.mobdev778.yusupova.features.splash.impl.data.model.BookListDto
import retrofit2.http.GET

internal interface VersesApi {

    @GET("/verses.json")
    suspend fun getVerses(): BookListDto
}
