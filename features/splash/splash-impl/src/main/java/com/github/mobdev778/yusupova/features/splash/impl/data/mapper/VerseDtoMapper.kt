package com.github.mobdev778.yusupova.features.splash.impl.data.mapper

import com.github.mobdev778.yusupova.core.domain.verses.Verse
import com.github.mobdev778.yusupova.features.splash.impl.data.model.VerseDto

internal class VerseDtoMapper {

    fun convert(dto: VerseDto): Verse = Verse(
        id = dto.id,
        title = dto.name,
        lines = dto.lines
    )
}
