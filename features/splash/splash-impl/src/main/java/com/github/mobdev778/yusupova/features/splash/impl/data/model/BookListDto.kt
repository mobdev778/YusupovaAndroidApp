package com.github.mobdev778.yusupova.features.splash.impl.data.model

import com.squareup.moshi.Json

internal class BookListDto(
    @Json(name = "version")
    val version: Int,

    @Json(name = "books")
    val books: List<BookDto>
)
