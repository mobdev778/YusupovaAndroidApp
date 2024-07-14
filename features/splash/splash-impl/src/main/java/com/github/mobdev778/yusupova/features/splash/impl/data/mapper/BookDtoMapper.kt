package com.github.mobdev778.yusupova.features.splash.impl.data.mapper

import com.github.mobdev778.yusupova.core.domain.verses.Book
import com.github.mobdev778.yusupova.features.splash.impl.data.model.BookDto

internal class BookDtoMapper {

    private val verseMapper by lazy {
        VerseDtoMapper()
    }

    fun convert(dto: BookDto): Book = Book(
        dto.id,
        dto.name,
        dto.verses.map { verseMapper.convert(it) }
    )
}
