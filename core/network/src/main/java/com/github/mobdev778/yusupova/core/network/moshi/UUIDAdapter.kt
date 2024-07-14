package com.github.mobdev778.yusupova.core.network.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.UUID

internal object UUIDAdapter {

    @FromJson
    fun fromJson(string: String) = UUID.fromString(string)

    @ToJson
    fun toJson(value: UUID) = value.toString()
}
