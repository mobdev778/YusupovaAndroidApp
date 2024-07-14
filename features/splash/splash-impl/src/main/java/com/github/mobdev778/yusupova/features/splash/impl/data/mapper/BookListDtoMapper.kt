package com.github.mobdev778.yusupova.features.splash.impl.data.mapper

import com.github.mobdev778.yusupova.core.domain.verses.BookList
import com.github.mobdev778.yusupova.features.splash.impl.data.model.BookListDto

internal class BookListDtoMapper {

    private val bookMapper by lazy {
        BookDtoMapper()
    }

    fun convert(dto: BookListDto): BookList = BookList(
        dto.version,
        dto.books.map { bookMapper.convert(it) }
    )
}
