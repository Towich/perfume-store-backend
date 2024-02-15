package com.towich.features.tables

import com.towich.database.tables.Tables
import com.towich.database.tables.TablesDTO
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.util.UUID

class TablesController {
    suspend fun performGetTablesByRestaurant(call: ApplicationCall) {
        val listOfTables: List<TablesDTO> =
            Tables.getAllTablesByRestaurantId(UUID.fromString(call.parameters["restaurant_id"]) ?: UUID.randomUUID())

        call.respond(listOfTables)
    }
}