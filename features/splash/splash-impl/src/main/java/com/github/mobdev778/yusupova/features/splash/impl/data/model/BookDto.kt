package com.github.mobdev778.yusupova.features.splash.impl.data.model

import com.squareup.moshi.Json
import java.util.UUID

internal class BookDto(
    @Json(name = "id")
    val id: UUID,

    @Json(name = "name")
    val name: String,

    @Json(name = "verses")
    val verses: List<VerseDto>
)
