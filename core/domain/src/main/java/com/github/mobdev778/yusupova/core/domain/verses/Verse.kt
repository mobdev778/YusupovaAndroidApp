package com.github.mobdev778.yusupova.core.domain.verses

import java.util.UUID

class Verse(
    val id: UUID,
    val title: String,
    val lines: List<String>
)
