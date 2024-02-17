package com.towich.database.tables

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID

object Tables: Table("tables") {
    private val id = Tables.uuid("id")
    private val restaurant_id = Tables.uuid("restaurant_id")
    private val is_free = Tables.bool("is_free")
    private val x = Tables.integer("x")
    private val y = Tables.integer("y")
    private val width_tiles = Tables.integer("width_tiles")
    private val height_tiles = Tables.integer("height_tiles")

    private fun resultRowToTable(row: ResultRow) = TablesDTO(
        id = row[id],
        restaurant_id = row[restaurant_id],
        is_free = row[is_free],
        x = row[x],
        y = row[y],
        width_tiles = row[width_tiles],
        height_tiles = row[height_tiles],
    )

    fun getAllTablesByRestaurantId(restaurant_id: UUID): List<TablesDTO> = transaction {
        Tables
            .select { Tables.restaurant_id eq restaurant_id }
            .map(::resultRowToTable)
    }

    fun getTableById(id: UUID): TablesDTO? = transaction {
        Tables
            .select { Tables.id eq id }
            .map(::resultRowToTable)
            .singleOrNull()
    }

    fun updateTableIsFreeById(tableId: UUID, isFree: Boolean): Boolean = transaction {
        Tables
            .update({ Tables.id eq tableId }) {
                it[is_free] = isFree
            } > 0
    }
}