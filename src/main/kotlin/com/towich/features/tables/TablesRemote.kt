package com.towich.features.tables

import com.towich.database.tables.TablesDTO
import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TablesRequestChangeIsFreeRemote(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val is_free: Boolean
)

@Serializable
data class TablesRespondRemote(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val restaurant_id: @Serializable(with = UUIDSerializer::class) UUID,
    val is_free: Boolean,
    val x: Int,
    val y: Int,
    val width_tiles: Int,
    val height_tiles: Int
){
    fun convertToTablesDTO() = TablesDTO(id, restaurant_id, is_free, x, y, width_tiles, height_tiles)
}

