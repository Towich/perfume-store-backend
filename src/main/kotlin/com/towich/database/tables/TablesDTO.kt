package com.towich.database.tables

import com.towich.features.tables.TablesRespondRemote
import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class TablesDTO(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val restaurant_id: @Serializable(with = UUIDSerializer::class) UUID,
    val is_free: Boolean,
    val x: Int,
    val y: Int,
    val width_tiles: Int,
    val height_tiles: Int
){
    fun convertToTablesRespondRemote() = TablesRespondRemote(id, restaurant_id, is_free, x, y, width_tiles, height_tiles)
}
