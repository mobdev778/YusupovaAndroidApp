package com.github.mobdev778.yusupova.core.domain.verses

import java.util.UUID

class Book(
    val id: UUID,
    val title: String,
    val verses: List<Verse>
)
